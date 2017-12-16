package org.txazo.algorithm.sort;

import java.util.List;

/**
 * 插入排序: 将一个元素插入到一个已排好序的列表中
 */
public class InsertionSort<T extends Comparable> extends AbstractSort<T> {

    public InsertionSort() {
        super("插入排序");
    }

    @Override
    public void sort(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j > 0 && less(j, j - 1, list); j--) {
                exchange(j - 1, j, list);
            }
        }
    }

}
