package org.txazo.wyot.cat.springcloud;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import com.netflix.hystrix.strategy.properties.HystrixProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.txazo.wyot.cat.common.ApplicationContext;
import org.txazo.wyot.cat.common.CatConstant;
import org.txazo.wyot.cat.common.CatContext;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class FeignHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

    private HystrixConcurrencyStrategy delegate;

    public FeignHystrixConcurrencyStrategy() {
        try {
            this.delegate = HystrixPlugins.getInstance().getConcurrencyStrategy();
            if (this.delegate instanceof FeignHystrixConcurrencyStrategy) {
                return;
            }

            HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance().getCommandExecutionHook();
            HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance().getEventNotifier();
            HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance().getMetricsPublisher();
            HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance().getPropertiesStrategy();
            HystrixPlugins.reset();
            HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
            HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
            HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
            HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
            HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register Sleuth Hystrix Concurrency Strategy", e);
        }
    }

    /**
     * 将当前线程参数传递到要调用的方法中去
     */
    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        buildCatLogAndMessage();
        return new WrappedCallable<>(callable, RequestContextHolder.getRequestAttributes());
    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize, HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        return this.delegate.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
        return this.delegate.getBlockingQueue(maxQueueSize);
    }

    @Override
    public <T> HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> rv) {
        return this.delegate.getRequestVariable(rv);
    }

    private static void buildCatLogAndMessage() {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes == null) {
                return;
            }

            HttpServletRequest request = requestAttributes.getRequest();

            Cat.logEvent("SOAClient", request.getRequestURI());
            Cat.logEvent("SOAClient.Method", request.getMethod(), Transaction.SUCCESS, request.getMethod().equals("GET") ? request.getQueryString() : StringUtils.EMPTY);
            Cat.logTrace("SOAClient.System", ApplicationContext.getAppName());

            Cat.Context context = new CatContext();
            Cat.logRemoteCallClient(context);

            requestAttributes.setAttribute(CatConstant.REMOTE_CALL_FLAG, "true", 0);
            requestAttributes.setAttribute(CatConstant.REMOTE_CALL_APP, ApplicationContext.getAppName(), 0);
            requestAttributes.setAttribute(Cat.Context.ROOT, context.getProperty(Cat.Context.ROOT), 0);
            requestAttributes.setAttribute(Cat.Context.PARENT, context.getProperty(Cat.Context.PARENT), 0);
            requestAttributes.setAttribute(Cat.Context.CHILD, context.getProperty(Cat.Context.CHILD), 0);
        } catch (Exception e) {
            Cat.logError(e);
        }
    }

    static class WrappedCallable<T> implements Callable<T> {

        private final Callable<T> target;
        private final RequestAttributes requestAttributes;

        public WrappedCallable(Callable<T> target, RequestAttributes requestAttributes) {
            this.target = target;
            this.requestAttributes = requestAttributes;
        }

        @Override
        public T call() throws Exception {
            try {
                RequestContextHolder.setRequestAttributes(requestAttributes);
                return target.call();
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        }

    }

}
