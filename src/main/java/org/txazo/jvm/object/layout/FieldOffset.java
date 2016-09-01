package org.txazo.jvm.object.layout;

import java.lang.reflect.Field;

public class FieldOffset implements Comparable<FieldOffset> {

    private long offset;
    private Class<?> classType;
    private Field field;

    public FieldOffset(long offset, Class<?> classType, Field field) {
        this.offset = offset;
        this.classType = classType;
        this.field = field;
    }

    @Override
    public String toString() {
        return offset + "\t\t" + field.getType().getSimpleName() + "\t\t" + classType.getSimpleName() + "." + field.getName();
    }

    @Override
    public int compareTo(FieldOffset o) {
        return (int) (this.offset - o.offset);
    }

}
