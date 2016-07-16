package org.txazo.designpattern.structural.decorator;

import org.junit.Test;

/**
 * 装饰器模式
 */
public class DecoratorTest {

    @Test
    public void test() {
        Controller controller = new IndexController();
        Interceptor loginInterceptor = new LoginInterceptor(controller);
        Interceptor logInterceptor = new LogInterceptor(loginInterceptor);
        logInterceptor.execute();
    }

}
