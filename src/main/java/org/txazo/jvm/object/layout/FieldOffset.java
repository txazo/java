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

    public long getOffset() {
        return offset;
    }

    public Field getField() {
        return field;
    }

    @Override
    public String toString() {
        return offset + "\t\t" + field.getName() + "\t\t" + field.getType().getSimpleName();
    }

    @Override
    public int compareTo(FieldOffset o) {
        return (int) (this.offset - o.offset);
    }

}
