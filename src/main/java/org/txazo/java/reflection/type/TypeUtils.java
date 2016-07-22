package org.txazo.java.reflection.type;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 泛型工具类
 */
public class TypeUtils {

    public static String parseType(Type type) {
        StringBuffer out = new StringBuffer();
        parseType(type, out);
        return out.toString();
    }

    private static void parseType(Type type, StringBuffer out) {
        if (type instanceof GenericArrayType) {
            parseType(((GenericArrayType) type).getGenericComponentType(), out);
            out.append("[]");
        } else if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            out.append(((Class) pType.getRawType()).getSimpleName());
            Type[] types = pType.getActualTypeArguments();
            out.append("<");
            for (int i = 0; i < types.length; i++) {
                out.append(i > 0 ? ", " : "");
                parseType(types[i], out);
            }
            out.append(">");
        } else if (type instanceof TypeVariable) {
            TypeVariable vType = (TypeVariable) type;
            out.append(vType.getName());
            Type[] types = vType.getBounds();
            if (ArrayUtils.isNotEmpty(types) && !Arrays.asList(types).contains(Object.class)) {
                for (int i = 0; i < types.length; i++) {
                    out.append(i == 0 ? " extends " : " & ");
                    parseType(types[i], out);
                }
            }
        } else if (type instanceof WildcardType) {
            WildcardType wType = (WildcardType) type;

            Type[] bounds = null;
            if (ArrayUtils.isNotEmpty(bounds = wType.getLowerBounds())) {
                out.append("? super ");
            } else {
                bounds = wType.getUpperBounds();
                out.append("? extends ");
            }
            for (int i = 0; i < bounds.length; i++) {
                out.append(i > 0 ? " & " : "");
                parseType(bounds[i], out);
            }
        } else {
            out.append(((Class) type).getSimpleName());
        }

    }

    private abstract class AbstractClass<T, W, P> {

    }

    private abstract class MyClass<T> extends AbstractClass<T, List<? extends List>, Integer[]> {

        private Map<T, List<? super ArrayList>[]> map;

        public abstract List<Map<? extends List, T>>[] get();

        public abstract void set(List<List<Map<T, ? super ArrayList>>[]> list);

    }

    private interface MyInterface<T extends List & Serializable, K> {

    }

    @Test
    public void test1() throws Exception {
        Assert.assertEquals("AbstractClass<T, List<? extends List>, Integer[]>", parseType(MyClass.class.getGenericSuperclass()));
        Assert.assertEquals("Map<T, List<? super ArrayList>[]>", parseType(MyClass.class.getDeclaredField("map").getGenericType()));
        Assert.assertEquals("List<Map<? extends List, T>>[]", parseType(MyClass.class.getMethod("get").getGenericReturnType()));
        Assert.assertEquals("List<List<Map<T, ? super ArrayList>>[]>", parseType(MyClass.class.getMethod("set", List.class).getGenericParameterTypes()[0]));
        Assert.assertEquals("T extends List & Serializable", parseType(MyInterface.class.getTypeParameters()[0]));
    }

}
