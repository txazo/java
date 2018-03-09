package org.txazo.algorithm.sort;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortCollection {

    public static void main(String[] args) {
        int[] data = data();
        selectSort(data);
        System.out.println(JSON.toJSONString(data));
        data = data();
        bubbleSort(data);
        System.out.println(JSON.toJSONString(data));
        data = data();
        insertSort(data);
        System.out.println(JSON.toJSONString(data));
        data = data();
        mergeSort(data);
        System.out.println(JSON.toJSONString(data));
    }

    private static int[] data() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        int[] data = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            data[i] = list.get(i);
        }
        return data;
    }

    /**
     * 选择排序
     */
    public static void selectSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int min = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = data[i];
                data[i] = data[min];
                data[min] = temp;
            }
        }
    }

    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] data) {
        for (int i = data.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     */
    public static void insertSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            for (int j = i; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    int temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    /**
     * 归并排序
     */
    public static void mergeSort(int[] data) {
        int[] temp = new int[data.length];
        sort(data, 0, data.length - 1, temp);
    }

    private static void sort(int[] data, int start, int end, int[] temp) {
        if (start < end) {
            int middle = (start + end) / 2;
            sort(data, start, middle, temp);
            sort(data, middle + 1, end, temp);
            merge(data, start, middle, end, temp);
        }
    }

    private static void merge(int[] data, int start, int middle, int end, int[] temp) {
        int t = 0;
        int i = start;
        int j = middle + 1;
        while (i <= middle && j <= end) {
            if (data[i] <= data[j]) {
                temp[t++] = data[i++];
            } else {
                temp[t++] = data[j++];
            }
        }
        while (i <= middle) {
            temp[t++] = data[i++];
        }
        while (j <= end) {
            temp[t++] = data[j++];
        }
        for (int k = 0; k < t; k++) {
            data[start + k] = temp[k];
        }
    }

}
