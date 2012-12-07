package com.harrycodeman.compression.huffman;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HuffmanDecompressInput {
    private InputStream input;

    public int totalCount;
    public HuffmanTreeNode root;
    public List<Integer> decompressedBytes = new ArrayList<Integer>();

    public HuffmanDecompressInput(InputStream input) {
        this.input = input;
    }

    public void decompress() throws Exception {
        // Total count decompression
        for (int numberFromRight = 3; numberFromRight >= 0; numberFromRight--) {
            int shift = numberFromRight*8;
            totalCount |= input.read() << shift;
        }
        // Huffman-tree decompression
        root = readNextNode();
        // Bytes decompression
        HuffmanTreeNode currentNode = root;
        while (decompressedBytes.size() < totalCount) {
            if (currentNode.hasValue()) {
                decompressedBytes.add(currentNode.getValue().getValue());
                currentNode = root;
            }
            else if (readBit()) {
                currentNode = currentNode.getBottom();
            }
            else {
                currentNode = currentNode.getTop();
            }
        }

        input.close();
    }

    int lastByte = 0;
    int index = 8;

    private HuffmanTreeNode readNextNode() throws Exception {
        if (readBit()) {
            return new HuffmanTreeNode(new HuffmanedByte(readByte()));
        }
        else {
            return new HuffmanTreeNode(readNextNode(), readNextNode());
        }
    }

    private boolean readBit() throws Exception {
        if (index == 8) {
            readByteIntoLastByte();
            index = 0;
        }
        return ((lastByte >> (7 - index++)) & 1) > 0;
    }

    private int readByte() throws Exception {
        if (index == 8) {
            return readByteIntoLastByte();
        }
        int result = (lastByte << index) & 255;
        readByteIntoLastByte();
        return result | (lastByte >> (8 - index));
    }

    private int readByteIntoLastByte() throws Exception {
        return lastByte = input.read();
    }
}
