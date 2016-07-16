package org.txazo.designpattern.structural.decorator;

/**
 * 登录拦截器
 */
public class LoginInterceptor extends Interceptor {

    public LoginInterceptor(Controller controller) {
        super(controller);
    }

    @Override
    public void interceptorBefore() {
        System.out.println("LoginInterceptor interceptorBefore");
    }

    @Override
    public void interceptorAfter() {
        System.out.println("LoginInterceptor interceptorAfter");
    }

}
