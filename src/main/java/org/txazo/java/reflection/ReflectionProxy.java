package org.txazo.java.reflection;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 反射 - 动态代理
 * <p>
 * 1. 创建委托对象
 * 2. 创建代理对象并执行
 * 3. 执行InvocationHandler.invoke()
 * 4. method.invoke()执行委托对象的方法
 */
public class ReflectionProxy {

    class InvocationHandlerProxy implements InvocationHandler {

        private Object target;

        /**
         * 传入委托对象
         */
        public <T> T getProxy(T target) {
            this.target = target;
            /** 生成代理对象并返回 */
            return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        }

        /**
         * 代理实现
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = null;
            System.out.println("println before");
            /** 执行委托对象的方法 */
            result = method.invoke(target, args);
            System.out.println("println after");
            return result;
        }

    }

    interface MyInterface {

        public void execute();

    }

    class MyClass implements MyInterface {

        @Override
        public void execute() {
            System.out.println("execute");
        }

    }

    @Test
    public void test1() {
        InvocationHandlerProxy handler = new InvocationHandlerProxy();
        /** 创建代理类 */
        MyInterface proxy = handler.getProxy(new MyClass());
        proxy.execute();
    }

}
