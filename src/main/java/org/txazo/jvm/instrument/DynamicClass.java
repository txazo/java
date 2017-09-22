package org.txazo.jvm.instrument;

import java.lang.reflect.Field;

public class DynamicClass {

    public void load() throws Exception {
        System.out.println(this.getClass().getName() + "@" + this.getClass().hashCode() + " load");
        System.out.println("ClassRedefinedCount: " + getClassRedefinedCount(this.getClass()));
        System.out.println(this);
    }

    private static int getClassRedefinedCount(Class<?> clazz) throws Exception {
        Field field = Class.class.getDeclaredField("classRedefinedCount");
        field.setAccessible(true);
        return (Integer) field.get(clazz);
    }

}
