package org.txazo.algorithm.sort;

import java.util.List;

/**
 * 选择排序: 从列表中选出最大(或最小的)的一个元素
 */
public class SelectionSort<T extends Comparable> extends AbstractSort<T> {

    public SelectionSort() {
        super("选择排序");
    }

    @Override
    public void sort(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            int min = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (less(j, min, list)) {
                    min = j;
                }
            }
            exchange(i, min, list);
        }
    }

}
