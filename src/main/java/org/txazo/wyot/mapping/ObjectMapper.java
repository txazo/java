package org.txazo.wyot.mapping;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 对象映射
 */
public abstract class ObjectMapper {

    private static final String[] DATETIME_FORMATS = {
            "yyyy-MM-dd HH:mm:ss",
            "yyyy/MM/dd HH:mm:ss",
            "yyyy-MM-dd",
            "yyyy/MM/dd"
    };

    private static final Map<UnionClass, UnionField> unionFieldCache = new ConcurrentHashMap<>();
    private static final Map<Class<?>, Field[]> classFieldCache = new ConcurrentHashMap<>();
    private static final Map<Class<?>, Class<?>> sameClassMap = new HashMap<>();
    private static final Map<Class<?>, Map<String, Field>> classFieldMap = new ConcurrentHashMap<>();
    private static final Set<Class<?>> numberClassSet = new HashSet<>();

    static {
        addSameClass(boolean.class, Boolean.class);
        addSameClass(byte.class, Byte.class);
        addSameClass(short.class, Short.class);
        addSameClass(int.class, Integer.class);
        addSameClass(long.class, Long.class);
        addSameClass(float.class, Float.class);
        addSameClass(double.class, Double.class);

        initNumberClassSet();
    }

    private static void addSameClass(Class<?> class1, Class<?> class2) {
        sameClassMap.put(class1, class2);
        sameClassMap.put(class2, class1);
    }

    private static void initNumberClassSet() {
        numberClassSet.add(byte.class);
        numberClassSet.add(short.class);
        numberClassSet.add(int.class);
        numberClassSet.add(long.class);
        numberClassSet.add(float.class);
        numberClassSet.add(double.class);
        numberClassSet.add(Byte.class);
        numberClassSet.add(Short.class);
        numberClassSet.add(Integer.class);
        numberClassSet.add(Long.class);
        numberClassSet.add(Float.class);
        numberClassSet.add(Double.class);
    }

    private static Field[] generateFields(Class<?> classType) {
        List<Field> fields = getAllFields(classType);
        return fields.stream().filter(f -> !Modifier.isStatic(f.getModifiers()))
                .peek(f -> f.setAccessible(true))
                .collect(Collectors.toList()).toArray(new Field[0]);
    }

    private static List<Field> getAllFields(Class<?> classType) {
        List<Field> fields = new ArrayList<>();
        Class<?> current = classType;
        while (current != Object.class) {
            fields.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }
        return fields;
    }

    private static Field[] getFields(Class<?> clazz) {
        Field[] fields = classFieldCache.get(clazz);
        if (fields == null) {
            classFieldCache.putIfAbsent(clazz, generateFields(clazz));
            fields = classFieldCache.get(clazz);
            for (Field field : fields) {
                field.setAccessible(true);
            }
        }
        return fields;
    }

    private static Map<String, Field> getFieldMap(Class<?> clazz) {
        Map<String, Field> fieldMap = classFieldMap.get(clazz);
        if (fieldMap == null) {
            classFieldMap.putIfAbsent(clazz, generateFieldMap(clazz));
            fieldMap = classFieldMap.get(clazz);
        }
        return fieldMap;
    }

    private static Map<String, Field> generateFieldMap(Class<?> clazz) {
        Field[] fields = getFields(clazz);
        Map<String, Field> fieldMap = new HashMap<>();
        boolean mapping = isMappingClass(clazz);
        for (Field f : fields) {
            addToFieldMap(f.getName().toLowerCase(), f, fieldMap, clazz);
            if (mapping) {
                MappingAlias alias = f.getAnnotation(MappingAlias.class);
                if (alias != null && !f.getName().equalsIgnoreCase(alias.value())) {
                    addToFieldMap(alias.value().toLowerCase(), f, fieldMap, clazz);
                }
            }
        }
        return fieldMap;
    }

    private static boolean isMappingClass(Class<?> clazz) {
        if (clazz.getAnnotation(Mapping.class) != null) {
            return true;
        } else if (clazz.getSuperclass() != Object.class) {
            return isMappingClass(clazz.getSuperclass());
        }
        return false;
    }

    private static void addToFieldMap(String name, Field field, Map<String, Field> fieldMap, Class<?> clazz) {
        if (StringUtils.isBlank(name)) {
            return;
        }

        if (fieldMap.containsKey(name)) {
            throw new RuntimeException("Field " + name + " already exists in " + clazz.getName());
        }

        fieldMap.put(name, field);
    }

    private static UnionField generateSameFields(Class<?> class1, Class<?> class2) {
        if (class1.getAnnotation(Mapping.class) != null) {
            return generateSameFields(getFields(class2), getFieldMap(class1), false);
        } else {
            return generateSameFields(getFields(class1), getFieldMap(class2), true);
        }
    }

    private static UnionField generateSameFields(Field[] fields, Map<String, Field> fieldMap, boolean order) {
        UnionField unionField = new UnionField();
        for (Field field : fields) {
            Field otherField = fieldMap.get(field.getName().toLowerCase());
            if (otherField != null && matchFieldType(field.getType(), otherField.getType())) {
                if (order) {
                    unionField.addFields(field, otherField);
                } else {
                    unionField.addFields(otherField, field);
                }
            }
        }
        return unionField;
    }

    private static boolean matchFieldType(Class<?> type1, Class<?> type2) {
        if (type1.equals(List.class) || type1.equals(Set.class) || type1.equals(Map.class)) {
            return false;
        }

        return type1.equals(type2)
                || type1.equals(sameClassMap.get(type2))
                || matchDateType(type1, type2)
                || matchNumberType(type1, type2);
    }

    private static boolean matchDateType(Class<?> type1, Class<?> type2) {
        if (type1 == Date.class) {
            return type2 == Calendar.class || type2 == Timestamp.class;
        } else if (type2 == Date.class) {
            return type1 == Calendar.class || type1 == Timestamp.class;
        }
        return false;
    }

    private static boolean matchNumberType(Class<?> type1, Class<?> type2) {
        return isNumberType(type1) && isNumberType(type2);
    }

    private static boolean isNumberType(Class<?> clazz) {
        return numberClassSet.contains(clazz);
    }

    private static UnionField getSameFields(Class<?> class1, Class<?> class2) {
        UnionClass unionClass = new UnionClass(class1, class2);
        UnionField unionField = unionFieldCache.get(unionClass);
        if (unionField == null) {
            unionField = generateSameFields(class1, class2);
            unionFieldCache.putIfAbsent(unionClass, unionField);
        }
        return unionField;
    }

    public static <S, D> D map(S s, Class<D> clazz) {
        return map(s, clazz, null);
    }

    public static <S, D> D map(S s, Class<D> clazz, MappingCallback<S, D> callback) {
        if (s == null || clazz == null) {
            return null;
        }

        D d = null;
        try {
            d = clazz.newInstance();
            inject(s, d);
            if (callback != null) {
                callback.callback(s, d);
            }
        } catch (Exception e) {
            throw new ObjectMappingException(String.format("map failed, sourceClass: %s, destClass: %s",
                    s.getClass().getName(), clazz.getName()));
        }
        return d;
    }

    public static <S, D> List<D> maps(List<S> list, Class<D> clazz) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().map(s -> map(s, clazz)).collect(Collectors.toList());
    }

    public static <S, D> List<D> maps(List<S> list, Class<D> clazz, MappingCallback<S, D> callback) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().map(s -> {
            D d = map(s, clazz);
            callback.callback(s, d);
            return d;
        }).collect(Collectors.toList());
    }

    public static <S, D> void inject(S s, D d) {
        if (s == null || d == null) {
            return;
        }
        try {
            UnionField unionField = getSameFields(s.getClass(), d.getClass());
            for (Map.Entry<Field, Field> entry : unionField.getFieldMap().entrySet()) {
                setField(s, entry.getKey(), d, entry.getValue());
            }
        } catch (Exception e) {
            throw new ObjectMappingException(String.format("map failed, sourceClass: %s, destClass: %s",
                    s.getClass().getName(), d.getClass().getName()));
        }
    }

    private static Object convertValue(Field field, String value) {
        if (field.getType() == String.class) {
            return value;
        } else if (StringUtils.isBlank(value)) {
            return null;
        } else if (field.getType() == byte.class || field.getType() == Byte.class) {
            return NumberUtils.toByte(value);
        } else if (field.getType() == short.class || field.getType() == Short.class) {
            return NumberUtils.toShort(value);
        } else if (field.getType() == int.class || field.getType() == Integer.class) {
            return NumberUtils.toInt(value);
        } else if (field.getType() == long.class || field.getType() == Long.class) {
            return NumberUtils.toLong(value);
        } else if (field.getType() == float.class || field.getType() == Float.class) {
            return NumberUtils.toFloat(value);
        } else if (field.getType() == double.class || field.getType() == Double.class) {
            return NumberUtils.toDouble(value);
        } else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (field.getType() == Timestamp.class) {
            return new Timestamp(parseDateTime(value, field).getTime());
        } else if (field.getType() == Date.class) {
            return parseDateTime(value, field);
        } else if (field.getType() == Calendar.class) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parseDateTime(value, field));
            return calendar;
        }
        return null;
    }

    private static Date parseDateTime(String value, Field field) {
        long time = -1;
        if ((time = NumberUtils.toLong(value, -1)) > 0) {
            return new Date(time);
        }

        try {
            return DateUtils.parseDateStrictly(value, DATETIME_FORMATS);
        } catch (ParseException e) {
            throw new ObjectMappingException(String.format("DateTime parse failed, class: %s, field: %s, value: %s",
                    field.getClass().getName(), field.getName(), value));
        }
    }

    private static void setField(Object source, Field sourceField, Object dest, Field destField) throws IllegalArgumentException, IllegalAccessException {
        Object value = sourceField.get(source);
        if (value != null) {
            if (sourceField.getType() == Date.class && destField.getType() == Timestamp.class) {
                value = toTimestamp((Date) value);
            } else if (sourceField.getType() == Date.class && destField.getType() == Calendar.class) {
                value = toCalendar((Date) value);
            } else if (sourceField.getType() == Calendar.class && destField.getType() == Date.class) {
                value = ((Calendar) value).getTime();
            } else if (matchNumberType(sourceField.getType(), destField.getType())) {
                value = parseNumberValue(value, destField);
            }
            destField.set(dest, value);
        }
    }

    private static Object parseNumberValue(Object value, Field destField) {
        Number number = (Number) value;
        if (destField.getType() == byte.class || destField.getType() == Byte.class) {
            return number.byteValue();
        } else if (destField.getType() == short.class || destField.getType() == Short.class) {
            return number.shortValue();
        } else if (destField.getType() == int.class || destField.getType() == Integer.class) {
            return number.intValue();
        } else if (destField.getType() == long.class || destField.getType() == Long.class) {
            return number.longValue();
        } else if (destField.getType() == float.class || destField.getType() == Float.class) {
            return number.floatValue();
        } else if (destField.getType() == double.class || destField.getType() == Double.class) {
            return number.doubleValue();
        }
        return value;
    }

    private static Timestamp toTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    private static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private static class UnionClass {

        private Class<?> class1;
        private Class<?> class2;

        public UnionClass(Class<?> class1, Class<?> class2) {
            this.class1 = class1;
            this.class2 = class2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UnionClass that = (UnionClass) o;
            if (class1 != null ? !class1.equals(that.class1) : that.class1 != null) return false;
            if (class2 != null ? !class2.equals(that.class2) : that.class2 != null) return false;
            return true;
        }

        @Override
        public int hashCode() {
            int result = class1 != null ? class1.hashCode() : 0;
            result = 31 * result + (class2 != null ? class2.hashCode() : 0);
            return result;
        }

    }

    private static class UnionField {

        private Map<Field, Field> fieldMap = new HashMap<>();

        private void addFields(Field field1, Field field2) {
            fieldMap.put(field1, field2);
        }

        private Map<Field, Field> getFieldMap() {
            return fieldMap;
        }
    }

}
