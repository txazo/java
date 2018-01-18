package org.txazo.tool.util;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * EmptyUtils
 */
public class EmptyUtils {

    /**
     * 空对象
     */
    public static <T> T getEmptyObject(Class<T> clazz) {
        return EmptyObjectFactory.getEmptyObject(clazz);
    }

    /**
     * 空数组
     */
    public static <T> T[] getEmptyArray(Class<T> clazz) {
        return EmptyArrayFactory.getEmptyArray(clazz);
    }

    /**
     * 空List
     */
    public static <T> List<T> getEmptyList(Class<T> clazz) {
        return (List<T>) Collections.EMPTY_LIST;
    }

    /**
     * 空Set
     */
    public static <T> Set<T> getEmptySet(Class<T> clazz) {
        return (Set<T>) Collections.EMPTY_SET;
    }

    /**
     * 空Map
     */
    public static <K, V> Map<K, V> getEmptyMap(Class<K> kClass, Class<V> vClass) {
        return (Map<K, V>) Collections.EMPTY_MAP;
    }

    /**
     * 享元模式
     */
    private static class EmptyArrayFactory {

        private static final ReentrantLock lock = new ReentrantLock();
        private static Map<Class, Object> emptyArrays = new HashMap<>();

        public static <T> T[] getEmptyArray(Class<T> clazz) {
            T[] tArray = (T[]) emptyArrays.get(clazz);
            if (tArray == null) {
                lock.lock();
                try {
                    tArray = (T[]) Array.newInstance(clazz, 0);
                    emptyArrays.put(clazz, tArray);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
            return tArray;
        }

    }

    /**
     * 享元模式
     */
    private static class EmptyObjectFactory {

        private static EmptyObjectProxy emptyObjectProxy = new EmptyObjectProxy();
        private static final ReentrantLock lock = new ReentrantLock();
        private static Map<Class, Object> emptyObjects = new HashMap<>();

        public static <T> T getEmptyObject(Class<T> clazz) {
            T t = (T) emptyObjects.get(clazz);
            if (t == null) {
                lock.lock();
                try {
                    t = emptyObjectProxy.getProxy(clazz.newInstance());
                    emptyObjects.put(clazz, t);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
            return t;
        }

    }

    private static class EmptyObjectProxy implements MethodInterceptor {

        public <T> T getProxy(T target) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(target.getClass());
            enhancer.setCallback(this);
            return (T) enhancer.create();
        }

        @Override
        public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            throw new UnsupportedOperationException(method.getDeclaringClass().getName() + " is empty");
        }

    }

}
