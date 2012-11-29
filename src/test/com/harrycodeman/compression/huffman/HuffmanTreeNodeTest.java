package test.com.harrycodeman.compression.huffman;

import com.harrycodeman.compression.huffman.HuffmanTreeNode;
import com.harrycodeman.compression.huffman.HuffmanedByte;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HuffmanTreeNodeTest {
    @Test
    public void testToString() throws Exception {
        HuffmanTreeNode node = new HuffmanTreeNode(
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
        assertEquals(
                "               0--------------------------------1               \n" +
                "       0----------------1              0----------------1       \n" +
                "   0--------1          69              6A          0--------1   \n" +
                " 0----1    66                                      67      68   \n" +
                " 64  65                                                         \n"
                , node.toString());
    }
}
