package test.com.harrycodeman.compression.huffman;

import com.harrycodeman.compression.CompressInput;
import com.harrycodeman.compression.huffman.ByteArrayHuffmanCompressOutput;
import com.harrycodeman.compression.huffman.CompressPrefixCodesTable;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class HuffmanCompressOutputTest {
    @Test
    public void testOutput() throws Exception {
        ByteArrayHuffmanCompressOutput huffmanOutput = new ByteArrayHuffmanCompressOutput(
                CompressPrefixCodesTable.buildPrefixTableForUncompressedStream(
                        new CompressInput(
                                new byte[]{
                                        100, 101, 101, 102, 102, 102, 102, 103, 103, 103, 103, 103, 104, 104, 104, 104,
                                        104, 104, 105, 105, 105, 105, 105, 105, 105, 105, 106, 106, 106, 106, 106, 106,
                                        106, 106, 106, 106
                                }
                        )
                )
        );
        huffmanOutput.output();
        // Total count:
        // 36
        // -----
        // 0 0 0 36
        //
        // Huffman-tree:
        // 0 0 0 0 1 [64] 1 [65] 1 [66] 1 [69] 0 1 [6A] 0 1 [67] 1 [68]
        // -----
        // 00001011 00100101 10010110 11001101 01101001 01011010 10010110 01111011 01000 (3 bits need)
        // 11       37       150      205      105      90       150      123
        //
        // Coded sequence
        // 0000 0001 0001 001 001 001 001 110 110 110 110 110 111 111 111 111 111 111 01 01 01 01 01 01 01 01 10 10 10 10 10 10 10 10 10 10
        // -----
        // 000 00001000 10010010 01001110 11011011 01101111 11111111 11111101 01010101 01010110 10101010 10101010 10 (6 bits need)
        // 64  8        146      78       219      111      255      253      85       86       170      170      128
        assertArrayEquals(
                new Object[]{
                        0, 0, 0, 36, 11, 37, 150, 205, 105, 90, 150, 123, 64, 8, 146, 78, 219, 111, 255, 253, 85, 86,
                        170, 170, 128
                },
                huffmanOutput.getResult().toArray());
    }
}
