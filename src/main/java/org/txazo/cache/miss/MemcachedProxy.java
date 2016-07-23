package org.txazo.cache.miss;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.spy.memcached.MemcachedClient;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.Arrays;

public class MemcachedProxy implements MethodInterceptor {

    private static final Class[] ArgumentTypes = new Class[]{InetSocketAddress[].class};

    private Enhancer enhancer = new Enhancer();

    public MemcachedClient getProxy(String ip, int port) {
        enhancer.setSuperclass(MemcachedClient.class);
        enhancer.setCallback(this);
        return (MemcachedClient) enhancer.create(ArgumentTypes, new Object[]{new InetSocketAddress[]{new InetSocketAddress(ip, port)}});
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (method.getName().equals("get") && Arrays.equals(method.getParameterTypes(), new Class<?>[]{String.class})) {
            syncBlock((String) args[0]);
        }
        return methodProxy.invokeSuper(object, args);
    }

    private void syncBlock(String key) {
    }

}
