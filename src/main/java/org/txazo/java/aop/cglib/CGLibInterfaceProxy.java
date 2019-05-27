package org.txazo.java.aop.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;
import org.txazo.java.aop.UserService;

import java.lang.reflect.Method;

public class CGLibInterfaceProxy implements MethodInterceptor {

    public <T> T getProxy(Class<T> interfaceType) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(interfaceType);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result = null;
        System.out.println("Proxy before");
        System.out.println("Proxy " + method.getName());
        System.out.println("Proxy after");
        return result;
    }

    @Test
    public void test() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/txazo/test");
        UserService proxy = new CGLibInterfaceProxy().getProxy(UserService.class);
        proxy.getUserName(1);
    }

}
