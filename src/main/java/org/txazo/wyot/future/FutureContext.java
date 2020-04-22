package org.txazo.wyot.future;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.message.spi.MessageTree;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

public class FutureContext {

    private volatile boolean rejected = false;
    private CountDownLatch counter;
    private List<FutureTask> tasks = new ArrayList<>();
    private AtomicReference<Exception> errorRef = new AtomicReference(null);
    private static ThreadLocal<Transaction> THREAD_LOCAL_TRANSACTION = new ThreadLocal<>();

    public static FutureContext getInstance() {
        return new FutureContext();
    }

    public FutureContext add(FutureTask task) {
        if (task != null) {
            tasks.add(task);
        }
        return this;
    }

    public FutureContext execute(ExecutorService executor) {
        if (tasks.size() > 0) {
            counter = new CountDownLatch(tasks.size());
            String messageId = Cat.getManager().getThreadLocalMessageTree().getMessageId();
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            for (FutureTask task : tasks) {
                String childId = Cat.createMessageId();
                Cat.logEvent(CatConstants.TYPE_REMOTE_CALL, "", Event.SUCCESS, childId);
                try {
                    executor.submit(new java.util.concurrent.FutureTask(new Callable() {

                        @Override
                        public Object call() throws Exception {
                            RequestContextHolder.setRequestAttributes(requestAttributes);
                            MessageTree localMessageTree = Cat.getManager().getThreadLocalMessageTree();
                            localMessageTree.setMessageId(childId);
                            localMessageTree.setParentMessageId(messageId);
                            localMessageTree.setRootMessageId(messageId);
                            Transaction t = Cat.newTransaction("FutureTask", task.getName());
                            THREAD_LOCAL_TRANSACTION.set(t);
                            return task.invoke();
                        }

                    }) {

                        @Override
                        protected void done() {
                            Transaction t = THREAD_LOCAL_TRANSACTION.get();
                            try {
                                Object result = get();
                                task.callback(result);
                                t.setStatus(Transaction.SUCCESS);
                            } catch (Exception e) {
                                t.setStatus(e);
                                Cat.logError(e);
                                setError(e);
                            } finally {
                                t.complete();
                                THREAD_LOCAL_TRANSACTION.remove();
                                counter.countDown();
                            }
                        }

                    });
                } catch (Exception e) {
                    rejected = true;
                    setError(e);
                    break;
                }
            }
        }
        return this;
    }

    public FutureContext sync() {
        if (!rejected && tasks.size() > 0) {
            try {
                counter.await();
            } catch (Exception e) {
                setError(e);
            }
        }
        return this;
    }

    public boolean hasError() {
        return errorRef.get() != null;
    }

    private void setError(Exception error) {
        this.errorRef.compareAndSet(null, error);
    }

    public Exception error() {
        return errorRef.get();
    }

}
