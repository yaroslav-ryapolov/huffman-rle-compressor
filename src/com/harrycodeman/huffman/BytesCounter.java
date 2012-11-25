package com.harrycodeman.huffman;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.sort;

public class BytesCounter {
    public static List<HuffmanedByte> reorderBytesByFrequencyAndConvertToList(Map<Integer, HuffmanedByte> countMap) {
        List<HuffmanedByte> result = new ArrayList<HuffmanedByte>(countMap.values());
        sort(result);
        return result;
    }

    public static List<CodedByte> buildPrefixCodeSystem(List<HuffmanedByte> huffmanedBytes) {
        while (huffmanedBytes.size() > 1) {
            HuffmanedByte b1 = huffmanedBytes.get(0);
            HuffmanedByte b2 = huffmanedBytes.get(1);
            huffmanedBytes.remove(0);
            huffmanedBytes.remove(0);
            huffmanedBytes.add(new CountedBytesUnderCodeBuilding(b1, b2));

            sort(huffmanedBytes);
        }

        List<CodedByte> result = new ArrayList<CodedByte>();
        expandPrefixCodes("", huffmanedBytes.get(0), result);
        return result;
    }

    private static void expandPrefixCodes(String seed, HuffmanedByte collapsedHuffmanedBytes, List<CodedByte> accumulator) {
        // TODO: code for one symbol collection!!!
        if (collapsedHuffmanedBytes.getClass() == HuffmanedByte.class) {
            accumulator.add(new CodedByte(collapsedHuffmanedBytes.getValue(), seed));
        }
        else {
            CountedBytesUnderCodeBuilding typed = (CountedBytesUnderCodeBuilding) collapsedHuffmanedBytes;
            expandPrefixCodes(seed + "0", typed.B1, accumulator);
            expandPrefixCodes(seed + "1", typed.B2, accumulator);
        }
    }
}
