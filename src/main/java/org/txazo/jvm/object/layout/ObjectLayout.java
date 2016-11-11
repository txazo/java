package org.txazo.jvm.object.layout;

import org.txazo.jvm.object.size.ObjectSize;
import sun.misc.Unsafe;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public abstract class ObjectLayout {

    private static final Map<Class, Integer> PrimaryTypeSize = new HashMap<Class, Integer>();

    static {
        PrimaryTypeSize.put(boolean.class, 1);
        PrimaryTypeSize.put(byte.class, 1);
        PrimaryTypeSize.put(char.class, 2);
        PrimaryTypeSize.put(short.class, 2);
        PrimaryTypeSize.put(int.class, 4);
        PrimaryTypeSize.put(float.class, 4);
        PrimaryTypeSize.put(long.class, 8);
        PrimaryTypeSize.put(double.class, 8);
    }

    private static int sizeOf(Class<?> classType) {
        Integer size = PrimaryTypeSize.get(classType);
        return size != null ? size : ObjectSize.referenceSize();
    }

    public static void printLayout(Object obj) {
        System.out.println("Class " + obj.getClass().getName());
        System.out.println("0\t\t_mark");
        System.out.println("8\t\t_metadata");

        if (obj.getClass().isArray()) {
            printArrayLayout(obj);
        } else {
            printObjectLayout(obj);
        }

        System.out.println(ObjectSize.sizeOf(obj) + "\t\ttotal");
        System.out.println("");
    }

    private static void printObjectLayout(Object obj) {
        List<Field> instanceFields = getDeclaredField(obj.getClass());
        List<FieldOffset> fieldOffsets = new ArrayList<FieldOffset>();
        for (Field field : instanceFields) {
            fieldOffsets.add(getFieldOffset(field));
        }

        Collections.sort(fieldOffsets);

        for (FieldOffset fieldOffset : fieldOffsets) {
            System.out.println(fieldOffset);
        }

        long size = ObjectSize.sizeOf(obj);
        FieldOffset last = fieldOffsets.get(fieldOffsets.size() - 1);
        int lastFieldSize = sizeOf(last.getField().getType());

        if (size > last.getOffset() + lastFieldSize) {
            System.out.println((last.getOffset() + lastFieldSize) + "\t\talign");
        }
    }

    private static void printArrayLayout(Object array) {
        int lengthSize = 4;
        int baseOffset = UnsafeHolder.unsafe.arrayBaseOffset(array.getClass());
        long size = ObjectSize.sizeOf(array);

        System.out.println((baseOffset - lengthSize) + "\t\tlength");

        Class<?> componentType = array.getClass().getComponentType();
        int length = Array.getLength(array);
        int componentSize = sizeOf(componentType);
        for (int i = 0; i < length; i++) {
            System.out.println((baseOffset + i * componentSize) + "\t\t[" + i + "]");
        }

        if (size > baseOffset + length * componentSize) {
            System.out.println((baseOffset + length * componentSize) + "\t\talign");
        }
    }

    private static List<Field> getDeclaredField(Class<?> classType) {
        List<Field> instanceFields = new ArrayList<Field>();
        Class<?> searchType = classType;
        while (Object.class != searchType && searchType != null) {
            Field[] fields = searchType.getDeclaredFields();
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    instanceFields.add(field);
                }
            }
            searchType = searchType.getSuperclass();
        }
        return instanceFields;
    }

    private static FieldOffset getFieldOffset(Field field) {
        long offset = UnsafeHolder.unsafe.objectFieldOffset(field);
        return new FieldOffset(offset, field.getDeclaringClass(), field);
    }

    private static class UnsafeHolder {

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

}
