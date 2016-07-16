package org.txazo.designpattern.structural.decorator;

/**
 * 拦截器
 */
public abstract class Interceptor implements Controller {

    private Controller controller;

    public Interceptor(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        interceptorBefore();
        controller.execute();
        interceptorAfter();
    }

    public abstract void interceptorBefore();

    public abstract void interceptorAfter();

}
