package org.txazo.jvm.object.layout;

import org.txazo.jvm.object.SizeOfObject;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 64位系统
 * 对象头: 16字节, 开启指针压缩: 12字节
 * 基本数据类型: byte boolean char short int float long double
 * 引用类型: 8字节, 开启指针压缩: 4字节
 * 数组类型:
 * 对齐填充: 8字节对齐
 * 父类field优先
 */
public abstract class ObjectLayout {

    /**
     * -XX:+UseCompressedOops -XX:-UseCompressedOops
     * VM Args: -javaagent:/Users/txazo/TxazoProject/java/target/java-1.0.jar
     */
    public static void main(String[] args) throws IOException {
        layout(new int[0]);
        layout(new int[1]);
        layout(new int[2]);
        layout(new HashMap<>());
        System.in.read();
    }

    public static void layout(Object obj) {
        List<Field> instanceFields = getDeclaredField(obj.getClass());
        List<FieldOffset> fieldOffsets = new ArrayList<FieldOffset>();
        for (Field field : instanceFields) {
            fieldOffsets.add(getFieldOffset(field));
        }

        Collections.sort(fieldOffsets);

        for (FieldOffset fieldOffset : fieldOffsets) {
            System.out.println(fieldOffset);
        }

        System.out.println(SizeOfObject.sizeOf(obj));
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

    private static class A {

        private int[] i;

    }

}
