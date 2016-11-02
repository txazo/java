package org.txazo.sun.misc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeHolder {

    public static final Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
