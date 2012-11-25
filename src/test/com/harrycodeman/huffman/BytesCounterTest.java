package test.com.harrycodeman.huffman;

import com.harrycodeman.huffman.BytesCounter;
import com.harrycodeman.huffman.CodedByte;
import com.harrycodeman.huffman.HuffmanedByte;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class BytesCounterTest {
    @Test
    public void testReorderBytesByFrequency() {
        Map<Integer, HuffmanedByte> countMap = new HashMap<Integer, HuffmanedByte>();
        countMap.put(99, new HuffmanedByte(99, 1));
        countMap.put(101, new HuffmanedByte(101, 7));
        countMap.put(100, new HuffmanedByte(100, 2));
        countMap.put(97, new HuffmanedByte(97, 3));
        assertArrayEquals(
            new HuffmanedByte[] {
                new HuffmanedByte(99, 1),
                new HuffmanedByte(100, 2),
                new HuffmanedByte(97, 3),
                new HuffmanedByte(101, 7)
            },
            BytesCounter.reorderBytesByFrequencyAndConvertToList(countMap).toArray());
    }

    @Test
    public void testBuildPrefixCodeSystem() {
        List<HuffmanedByte> huffmanedBytes = new ArrayList<HuffmanedByte>();
        huffmanedBytes.add(new HuffmanedByte(100, 1));
        huffmanedBytes.add(new HuffmanedByte(101, 2));
        huffmanedBytes.add(new HuffmanedByte(102, 4));
        huffmanedBytes.add(new HuffmanedByte(103, 5));
        huffmanedBytes.add(new HuffmanedByte(104, 6));
        huffmanedBytes.add(new HuffmanedByte(105, 8));
        huffmanedBytes.add(new HuffmanedByte(106, 10));
        List<CodedByte> expected = new ArrayList<CodedByte>();
        expected.add(new CodedByte(100, "0000"));
        expected.add(new CodedByte(101, "0001"));
        expected.add(new CodedByte(102, "001"));
        expected.add(new CodedByte(103, "110"));
        expected.add(new CodedByte(104, "111"));
        expected.add(new CodedByte(105, "01"));
        expected.add(new CodedByte(106, "10"));
        assertListContentEquals(expected, BytesCounter.buildPrefixCodeSystem(huffmanedBytes));
    }

    private void assertListContentEquals(List<CodedByte> expected, List<CodedByte> actual) {
        assertEquals(expected.size(), actual.size());
        for (CodedByte e : expected) {
            int i = 0;
            for (CodedByte a : actual) {
                if (e.equals(a)) {
                    break;
                }
                i++;
            }
            assertTrue(outputArray(actual.toArray()), i < actual.size());
        }
    }

    private String outputArray(Object[] array) {
        String result = "";
        for (Object b : array) {
            result += b.toString() + " ";
        }
        return result;
    }
}
