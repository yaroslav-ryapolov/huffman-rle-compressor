package test.com.harrycodeman.huffman;

import com.harrycodeman.huffman.BytesCounter;
import com.harrycodeman.huffman.CountedByte;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class BytesCounterTest {
    @Test
    public void testCountOneByteRepetition() {
        Map<Integer, Integer> expected = new HashMap<Integer, Integer>();
        expected.put((int)'a', 3);
        assertCounterEquals(expected, BytesCounter.countBytes(new int[]{'a', 'a', 'a'}));
    }

    @Test
    public void testCountTwoDifferentByteSequence() {
        Map<Integer, Integer> expected = new HashMap<Integer, Integer>();
        expected.put((int)'b', 3);
        expected.put((int)'c', 2);
        assertCounterEquals(expected, BytesCounter.countBytes(new int[] { 'b', 'c', 'b', 'b', 'c' }));
    }

    @Test
    public void testCountOneSingleByte() {
        Map<Integer, Integer> expected = new HashMap<Integer, Integer>();
        expected.put((int) 'd', 1);
        assertCounterEquals(expected, BytesCounter.countBytes(new int[] { 'd' }));
    }

    private void assertCounterEquals(Map<Integer, Integer> expected, Map<Integer, CountedByte> actual) {
        assertEquals(expected.size(), actual.size());
        assertArrayEquals(expected.keySet().toArray(), actual.keySet().toArray());
        for (int key : expected.keySet()) {
            assertEquals(expected.get(key), (Integer)actual.get(key).getReps());
        }
    }

    @Test
    public void testReorderBytesByDescFrequencyAscOrderedBytes() {
        Map<Integer, CountedByte> countMap = new HashMap<Integer, CountedByte>();
        countMap.put(99, new CountedByte(99, 1));
        countMap.put(101, new CountedByte(101, 7));
        countMap.put(100, new CountedByte(100, 2));
        countMap.put(97, new CountedByte(97, 3));
        assertArrayEquals(new CountedByte[] {
                new CountedByte(101, 7),
                new CountedByte(97, 3),
                new CountedByte(100, 2),
                new CountedByte(99, 1)
            },
            BytesCounter.reorderBytesByDescFrequency(countMap).toArray());
    }
}
