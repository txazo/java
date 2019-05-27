package org.txazo.java.aop.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;
import org.txazo.java.aop.UserService;
import org.txazo.java.aop.UserServiceImpl;

import java.lang.reflect.Method;

public class CGLibClassProxy implements MethodInterceptor {

    public <T> T getProxy(T target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result = null;
        System.out.println("Proxy before");
        result = proxy.invokeSuper(object, args);
        System.out.println("Proxy after");
        return result;
    }

    @Test
    public void test() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/txazo/test");
        UserService userService = new UserServiceImpl();
        UserService proxy = new CGLibClassProxy().getProxy(userService);
        proxy.getUserName(1);
    }

}
