package test.com.harrycodeman.compression.huffman;

import com.harrycodeman.compression.huffman.HuffmanDecompressInput;
import com.harrycodeman.compression.huffman.HuffmanTreeNode;
import com.harrycodeman.compression.huffman.HuffmanedByte;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class HuffmanDecompressInputTest {
    @Test
    public void testDecompress() throws Exception {
        ByteArrayInputStream stream = new ByteArrayInputStream(
                new byte[] {
                        0, 0, 0, 36, 11, 37, -106, -51, 105, 90, -106, 123, 64, 8, -110, 78, -37, 111, -1, -3, 85, 86,
                        -86, -86, -128
                });
        HuffmanDecompressInput decompressInput = new HuffmanDecompressInput(stream);
        decompressInput.decompress();
        assertEquals(36, decompressInput.totalCount);
        assertEquals(
                new HuffmanTreeNode(
                        new HuffmanTreeNode(
                                new HuffmanTreeNode(
                                        new HuffmanTreeNode(
                                                new HuffmanTreeNode(new HuffmanedByte(100)),
                                                new HuffmanTreeNode(new HuffmanedByte(101))
                                        ),
                                        new HuffmanTreeNode(new HuffmanedByte(102))
                                ),
                                new HuffmanTreeNode(new HuffmanedByte(105))
                        ),
                        new HuffmanTreeNode(
                                new HuffmanTreeNode(new HuffmanedByte(106)),
                                new HuffmanTreeNode(
                                        new HuffmanTreeNode(new HuffmanedByte(103)),
                                        new HuffmanTreeNode(new HuffmanedByte(104))
                                )
                        )
                ),
                decompressInput.root
        );
        assertArrayEquals(
                new Object[]{
                        100, 101, 101, 102, 102, 102, 102, 103, 103, 103, 103, 103, 104, 104, 104, 104,
                        104, 104, 105, 105, 105, 105, 105, 105, 105, 105, 106, 106, 106, 106, 106, 106,
                        106, 106, 106, 106
                },
                decompressInput.decompressedBytes.toArray()
        );
    }
}
