package org.txazo.wyot.mapping;

import org.apache.commons.beanutils.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 对象映射
 */
public abstract class ObjectMapper2 {

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
            throw new ObjectMappingException(e);
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

    public static <S, D> void inject(S s, D d) throws Exception {
        if (s == null || d == null) {
            return;
        }

        BeanUtils.copyProperties(s, d);
    }

}
