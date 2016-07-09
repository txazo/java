package org.txazo.java.aop.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.txazo.java.aop.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @see java.lang.reflect.Proxy#newProxyInstance(ClassLoader, Class[], InvocationHandler)
 * @see java.lang.reflect.WeakCache#get(Object, Object)
 * @see java.lang.reflect.Proxy.ProxyClassFactory#apply(ClassLoader, Class[])
 * @see sun.misc.ProxyGenerator#generateProxyClass(String, Class[])
 * @see sun.misc.ProxyGenerator#generateClassFile()
 */
public class JdkProxy<T> implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdkProxy.class);

    private T target;

    public JdkProxy(T target) {
        this.target = target;
    }

    public T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        LOGGER.info("proxy before");
        result = method.invoke(target, args);
        LOGGER.info("proxy after");
        return result;
    }

}

final class $Proxy0 extends Proxy implements UserService {

    private static Method m0;
    private static Method m1;
    private static Method m2;
    private static Method m3;

    static {
        try {
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[]{Class.forName("java.lang.Object")});
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
            m3 = Class.forName("org.txazo.java.aop.UserService").getMethod("addUser", new Class[]{Class.forName("java.lang.String")});
        } catch (NoSuchMethodException noSuchMethodException) {
            throw new NoSuchMethodError(noSuchMethodException.getMessage());
        } catch (ClassNotFoundException classNotFoundException) {
            throw new NoClassDefFoundError(classNotFoundException.getMessage());
        }
    }

    public $Proxy0(InvocationHandler invocationHandler) {
        super(invocationHandler);
    }

    @Override
    public final int hashCode() {
        try {
            return ((Integer) super.h.invoke(this, m0, null)).intValue();
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    @Override
    public final boolean equals(Object obj) {
        try {
            return ((Boolean) super.h.invoke(this, m1, new Object[]{obj})).booleanValue();
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    @Override
    public final String toString() {
        try {
            return (String) super.h.invoke(this, m2, null);
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    @Override
    public void addUser(String userName) {
        try {
            super.h.invoke(this, m3, null);
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

}
