package org.txazo.algorithm.sort;

import java.util.List;

public interface Sort<T extends Comparable> {

    void sort(List<T> list);

    void sort(T[] array);

    String getName();

}
