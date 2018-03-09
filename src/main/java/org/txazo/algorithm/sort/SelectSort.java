package org.txazo.algorithm.sort;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class SelectSort {

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

    public static void main(String[] args) {
        int[] data = new int[]{12, 34, 1, 43, 6, 24, 65, 3, 34};
        selectSort(data);
        System.out.println(JSON.toJSONString(data));
    }

}
