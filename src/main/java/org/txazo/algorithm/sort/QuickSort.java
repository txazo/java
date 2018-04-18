package org.txazo.algorithm.sort;

import com.alibaba.fastjson.JSON;

public class QuickSort {

    public static void main(String[] args) {
        int[] data = new int[]{12, 34, 1, 43, 6, 24, 65, 3, 34};
        quickSort(data);
        System.out.println(JSON.toJSONString(data));
    }

    public static void quickSort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    private static void sort(int[] data, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = partition(data, start, end);
        sort(data, start, middle - 1);
        sort(data, middle + 1, end);
    }

    private static int partition(int[] data, int start, int end) {
        int i = start;
        int j = end + 1;
        while (true) {
            while (data[++i] < data[start]) {
                if (i == end) {
                    break;
                }
            }
            while (data[--j] > data[start]) {
                if (j == start) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
        int temp = data[start];
        data[start] = data[j];
        data[j] = temp;
        return j;
    }

}
