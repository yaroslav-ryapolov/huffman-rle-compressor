package test.com.harrycodeman.huffman;

import com.harrycodeman.MemoryCompressByteStream;
import com.harrycodeman.huffman.HuffmanTreeNode;
import com.harrycodeman.huffman.HuffmanedByte;
import com.harrycodeman.huffman.PrefixCodesTableForCompress;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class PrefixCodesTableForCompressTest extends PrefixCodesTableForCompress {
    @Test
    public void testCountOneByteRepetition() {
        assertCounterEquals(new HuffmanedByte[] { new HuffmanedByte('a', 3) },
            PrefixCodesTableForCompress.buildPrefixTable(new MemoryCompressByteStream("aaa")));
    }

    @Test
    public void testCountTwoDifferentByteSequence() {
        assertCounterEquals(new HuffmanedByte[] {
                new HuffmanedByte('b', 3),
                new HuffmanedByte('c', 2),
            },
            PrefixCodesTableForCompress.buildPrefixTable(new MemoryCompressByteStream("bcbbc")));
    }

    @Test
    public void testCountOneSingleByte() {
        assertCounterEquals(new HuffmanedByte[] { new HuffmanedByte('d', 1) },
            PrefixCodesTableForCompress.buildPrefixTable(new MemoryCompressByteStream("d")));
    }

    private void assertCounterEquals(HuffmanedByte[] expected, PrefixCodesTableForCompress actual) {
        assertEquals(expected.length, actual.size());
        for (HuffmanedByte b : expected) {
            assertEquals(b.getReps(), actual.getRepsForByte(b.getValue()));
        }
    }

    @Test
    public void testBuildHuffmanTree() {
        Map<Integer, HuffmanedByte> mapOfBytes = new HashMap<Integer, HuffmanedByte>();
        mapOfBytes.put(100, new HuffmanedByte(100, 1));
        mapOfBytes.put(101, new HuffmanedByte(101, 2));
        mapOfBytes.put(102, new HuffmanedByte(102, 4));
        mapOfBytes.put(103, new HuffmanedByte(103, 5));
        mapOfBytes.put(104, new HuffmanedByte(104, 6));
        mapOfBytes.put(105, new HuffmanedByte(105, 8));
        mapOfBytes.put(106, new HuffmanedByte(106, 10));
        PrefixCodesTableForCompress table = PrefixCodesTableForCompress.getPrefixTableForMap(mapOfBytes);
        HuffmanTreeNode expected = new HuffmanTreeNode(
            new HuffmanTreeNode(
                new HuffmanTreeNode(
                    new HuffmanTreeNode(
                        new HuffmanTreeNode(new HuffmanedByte(100, 1, "0000")),
                        new HuffmanTreeNode(new HuffmanedByte(101, 2, "0001")),
                        false
                    ),
                    new HuffmanTreeNode(new HuffmanedByte(102, 4, "001")),
                    false
                ),
                new HuffmanTreeNode(new HuffmanedByte(105, 8, "01")),
                false
            ),
            new HuffmanTreeNode(
                new HuffmanTreeNode(new HuffmanedByte(106, 10, "10")),
                new HuffmanTreeNode(
                    new HuffmanTreeNode(new HuffmanedByte(103, 5, "110")),
                    new HuffmanTreeNode(new HuffmanedByte(104, 6, "111")),
                    false
                ),
                false
            ),
            false
        );
        assertEquals(expected, table.getRoot());
    }
}
