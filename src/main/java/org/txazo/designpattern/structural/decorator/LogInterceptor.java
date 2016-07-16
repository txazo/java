package org.txazo.designpattern.structural.decorator;

/**
 * 日志拦截器
 */
public class LogInterceptor extends Interceptor {

    public LogInterceptor(Controller controller) {
        super(controller);
    }

    @Override
    public void interceptorBefore() {
        System.out.println("LogInterceptor interceptorBefore");
    }

    @Override
    public void interceptorAfter() {
        System.out.println("LogInterceptor interceptorAfter");
    }

}
