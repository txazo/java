package org.txazo.jdk8.lambda;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class LamdbaUtils {

    public static <S, D> List<D> map(Collection<S> collection, Function<? super S, ? extends D> mapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.EMPTY_LIST;
        }
        return collection.stream().map(mapper).filter(e -> e != null).distinct().collect(Collectors.toList());
    }

    public static <S, D> List<D> map(Collection<S> collection, Predicate<? super S> filter, Function<? super S, ? extends D> mapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.EMPTY_LIST;
        }
        return collection.stream().filter(filter).map(mapper).filter(e -> e != null).distinct().collect(Collectors.toList());
    }

    public static <K, V> Map<K, V> toMap(Collection<V> collection, Function<? super V, ? extends K> keyMapper) {
        if (collection == null) {
            return Collections.EMPTY_MAP;
        }
        return collection.stream().collect(Collectors.toMap(keyMapper, v -> v));
    }

    public static <K, V> Map<K, List<V>> groupBy(Collection<V> collection, Function<? super V, ? extends K> classifier) {
        if (collection == null) {
            return Collections.EMPTY_MAP;
        }
        return collection.stream().collect(Collectors.groupingBy(classifier));
    }

}
