package test.com.harrycodeman.compression.huffman;

import com.harrycodeman.compression.CompressInputImpl;
import com.harrycodeman.compression.huffman.CompressPrefixCodesTable;
import com.harrycodeman.compression.huffman.HuffmanTreeNode;
import com.harrycodeman.compression.huffman.HuffmanedByte;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class CompressPrefixCodesTableTest extends CompressPrefixCodesTable {
    @Test
    public void testCountOneByteRepetition() throws IOException {
        assertCounterEquals(new HuffmanedByte[] { new HuffmanedByte('a', 3) },
            CompressPrefixCodesTable.buildPrefixTableForUncompressedStream(new CompressInputImpl("aaa"))
        );
    }

    @Test
    public void testCountTwoDifferentByteSequence() throws IOException {
        assertCounterEquals(new HuffmanedByte[] {
                new HuffmanedByte('b', 3),
                new HuffmanedByte('c', 2),
            },
            CompressPrefixCodesTable.buildPrefixTableForUncompressedStream(new CompressInputImpl("bcbbc"))
        );
    }

    @Test
    public void testCountOneSingleByte() throws IOException {
        assertCounterEquals(new HuffmanedByte[] { new HuffmanedByte('d', 1) },
            CompressPrefixCodesTable.buildPrefixTableForUncompressedStream(new CompressInputImpl("d"))
        );
    }

    private void assertCounterEquals(HuffmanedByte[] expected, CompressPrefixCodesTable actual) {
        assertEquals(expected.length, actual.differentBytesCount());
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
        CompressPrefixCodesTable table = CompressPrefixCodesTable.getPrefixTableForMap(mapOfBytes);
        assertEquals(
                new HuffmanTreeNode(
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
                ),
                table.getRoot());
    }

    @Test
    public void testCompress() throws IOException {
        CompressPrefixCodesTable table = CompressPrefixCodesTable.buildPrefixTableForUncompressedStream(
                new CompressInputImpl(
                        new byte[]{
                                100, 101, 101, 102, 102, 102, 102, 103, 103, 103, 103, 103, 104, 104, 104, 104, 104,
                                104, 105, 105, 105, 105, 105, 105, 105, 105, 106, 106, 106, 106, 106, 106, 106, 106,
                                106, 106
                        }
                )
        );
        assertArrayEquals(
                new HuffmanedByte[] {
                        new HuffmanedByte(100, 1, "0000"), new HuffmanedByte(101, 2, "0001"),
                        new HuffmanedByte(101, 2, "0001"), new HuffmanedByte(102, 4, "001"),
                        new HuffmanedByte(102, 4, "001"), new HuffmanedByte(102, 4, "001"),
                        new HuffmanedByte(102, 4, "001"), new HuffmanedByte(103, 5, "110"),
                        new HuffmanedByte(103, 5, "110"), new HuffmanedByte(103, 5, "110"),
                        new HuffmanedByte(103, 5, "110"), new HuffmanedByte(103, 5, "110"),
                        new HuffmanedByte(104, 6, "111"), new HuffmanedByte(104, 6, "111"),
                        new HuffmanedByte(104, 6, "111"), new HuffmanedByte(104, 6, "111"),
                        new HuffmanedByte(104, 6, "111"), new HuffmanedByte(104, 6, "111"),
                        new HuffmanedByte(105, 8, "01"), new HuffmanedByte(105, 8, "01"),
                        new HuffmanedByte(105, 8, "01"), new HuffmanedByte(105, 8, "01"),
                        new HuffmanedByte(105, 8, "01"), new HuffmanedByte(105, 8, "01"),
                        new HuffmanedByte(105, 8, "01"), new HuffmanedByte(105, 8, "01"),
                        new HuffmanedByte(106, 10, "10"), new HuffmanedByte(106, 10, "10"),
                        new HuffmanedByte(106, 10, "10"), new HuffmanedByte(106, 10, "10"),
                        new HuffmanedByte(106, 10, "10"), new HuffmanedByte(106, 10, "10"),
                        new HuffmanedByte(106, 10, "10"), new HuffmanedByte(106, 10, "10"),
                        new HuffmanedByte(106, 10, "10"), new HuffmanedByte(106, 10, "10"),
                },
                table.compress().toArray()
        );
    }
}
