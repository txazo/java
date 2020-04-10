package org.txazo.leetcode;

import java.util.HashMap;
import java.util.Map;

public class FourSumCount {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        Map<Integer, Integer> sumMap1 = calculate(A, B);
        Map<Integer, Integer> sumMap2 = calculate(C, D);
        for (int x : sumMap1.keySet()) {
            if (sumMap2.containsKey(-x)) {
                count += sumMap1.get(x) * sumMap2.get(-x);
            }
        }
        return count;
    }

    private static Map<Integer, Integer> calculate(int[] a, int[] b) {
        int sum = -1;
        Map<Integer, Integer> sumMap = new HashMap<>();
        for (int i : a) {
            for (int j : b) {
                sum = i + j;
                sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
            }
        }
        return sumMap;
    }

}
