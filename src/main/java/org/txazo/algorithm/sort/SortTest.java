package org.txazo.algorithm.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        sort(list, new SelectionSort<>());
        sort(list, new InsertionSort<>());
    }

    private static <T extends Comparable> void sort(List<T> list, Sort<T> sort) {
        System.out.println("--------------------< " + sort.getName() + " >--------------------");
        Collections.shuffle(list);
        System.out.println("[shuffle]\t" + list);
        sort.sort(list);
        System.out.println("[sort]\t\t" + list + "\n");
    }

}
