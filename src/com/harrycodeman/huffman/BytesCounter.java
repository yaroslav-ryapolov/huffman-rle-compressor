package com.harrycodeman.huffman;

import java.util.HashMap;
import java.util.Map;

public class BytesCounter {
    public static Map<Integer, Integer> countBytes(int[] bytes) {
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for (int b : bytes) {
            int count = 1;
            if (countMap.containsKey(b)) {
                count += countMap.get(b);
            }
            countMap.put(b, count);
        }
        return countMap;
    }
}
