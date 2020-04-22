package org.txazo.wyot.future2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class FutureContext {

    private CountDownLatch counter;
    private List<FutureTask> tasks = new ArrayList<>();

    public static FutureContext getInstance() {
        return new FutureContext();
    }

    public FutureContext addTasks(List<? extends Runnable> tasks) {
        for (Runnable r : tasks) {
            this.tasks.add(new DoneFutureTask(r, null, this));
        }
        return this;
    }

    public FutureContext execute(ExecutorService executor) {
        if (tasks.size() > 0) {
            counter = new CountDownLatch(tasks.size());
            for (FutureTask task : tasks) {
                executor.submit(task);
            }
        }
        return this;
    }

    public void sync() throws InterruptedException {
        if (tasks.size() > 0) {
            counter.await();
        }
    }

    protected void done(FutureTask futureTask) {
        counter.countDown();
    }

}
