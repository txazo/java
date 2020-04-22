package org.txazo.wyot.future2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class DoneFutureTask<V> extends FutureTask<V> {

    private FutureContext context;

    public DoneFutureTask(Callable<V> callable, FutureContext context) {
        super(callable);
        this.context = context;
    }

    public DoneFutureTask(Runnable runnable, V result, FutureContext context) {
        super(runnable, result);
        this.context = context;
    }

    @Override
    protected void done() {
        context.done(this);
    }

}
