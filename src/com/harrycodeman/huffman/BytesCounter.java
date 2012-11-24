package com.harrycodeman.huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.reverseOrder;
import static java.util.Collections.sort;

public class BytesCounter {
    public static Map<Integer, CountedByte> countBytes(int[] bytes) {
        HashMap<Integer, CountedByte> countMap = new HashMap<Integer, CountedByte>();
        for (int b : bytes) {
            CountedByte countedByte;
            if (countMap.containsKey(b)) {
                countedByte = countMap.get(b);
            }
            else {
                countedByte = new CountedByte(b);
                countMap.put(b, countedByte);
            }
            countedByte.incrementReps();
        }
        return countMap;
    }

    public static List<CountedByte> reorderBytesByDescFrequency(Map<Integer, CountedByte> countMap) {
        List<CountedByte> result = new ArrayList<CountedByte>();
        for (int key : countMap.keySet()) {
            result.add(countMap.get(key));
        }
        sort(result, reverseOrder());
        return result;
    }
}
