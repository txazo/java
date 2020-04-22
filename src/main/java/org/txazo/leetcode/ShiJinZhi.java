package org.txazo.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShiJinZhi {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(toIntArray(1)));
        System.out.println(JSON.toJSONString(toIntArray(10)));
        System.out.println(JSON.toJSONString(toIntArray(12345)));
    }

    public static List<Integer> toIntArray(int i) {
        List<Integer> result = new ArrayList<>();
        int m = i;
        int n = 0;
        int k = 0;
        while (m >= 10) {
            n = m / 10;
            k = m - n * 10;
            m = n;
            result.add(k);
        }
        result.add(m);
        Collections.reverse(result);
        return result;
    }

}
