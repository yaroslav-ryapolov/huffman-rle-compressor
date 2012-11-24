package test.com.harrycodeman.huffman;

import com.harrycodeman.huffman.BytesCounter;
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
        expected.put((int)'d', 1);
        assertCounterEquals(expected, BytesCounter.countBytes(new int[] { 'd' }));
    }

    private void assertCounterEquals(Map<Integer, Integer> expected, Map<Integer, Integer> actual) {
        assertEquals(expected.size(), actual.size());
        assertArrayEquals(expected.keySet().toArray(), actual.keySet().toArray());
        for (int key : expected.keySet()) {
            assertEquals(expected.get(key), actual.get(key));
        }
    }
}
