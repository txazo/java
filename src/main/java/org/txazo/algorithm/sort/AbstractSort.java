package org.txazo.algorithm.sort;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractSort<T extends Comparable> implements Sort<T> {

    private String name;

    public AbstractSort(String name) {
        this.name = name;
    }

    @Override
    public void sort(T[] array) {
        sort(Arrays.asList(array));
    }

    boolean less(int i, int j, List<T> list) {
        return list.get(i).compareTo(list.get(j)) < 0;
    }

    void exchange(int i, int j, List<T> list) {
        if (i != j) {
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    @Override
    public String getName() {
        return name;
    }

}
