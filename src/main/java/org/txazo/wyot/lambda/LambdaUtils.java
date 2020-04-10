package org.txazo.wyot.lambda;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class LambdaUtils {

    private LambdaUtils() {

    }

    /**
     * 集合映射
     */
    public static <S, D> List<D> map(Collection<S> collection, Function<? super S, ? extends D> mapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().map(mapper).filter(e -> e != null).collect(Collectors.toList());
    }

    /**
     * 集合映射
     */
    public static <S, D> List<D> map(Collection<S> collection, Predicate<? super S> filter, Function<? super S, ? extends D> mapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().filter(filter).map(mapper).filter(e -> e != null).collect(Collectors.toList());
    }

    /**
     * 集合转换为Map
     */
    public static <K, V> Map<K, V> toMap(Collection<V> collection, Function<? super V, ? extends K> keyMapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream().collect(Collectors.toMap(keyMapper, v -> v));
    }

    /**
     * 集合转换为Map
     */
    public static <K, V> Map<K, V> toMap(Collection<V> collection, Predicate<? super V> filter, Function<? super V, ? extends K> keyMapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream().filter(filter).collect(Collectors.toMap(keyMapper, v -> v));
    }

    /**
     * 集合分组
     */
    public static <K, V> Map<K, List<V>> groupBy(Collection<V> collection, Function<? super V, ? extends K> classifier) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream().collect(Collectors.groupingBy(classifier));
    }

}
