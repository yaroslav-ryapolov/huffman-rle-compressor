package com.harrycodeman.compression.huffman;

import java.util.ArrayList;
import java.util.List;

public class HuffmanCompressOutput {
    public List<Integer> result = new ArrayList<Integer>();
    private PrefixCodesTable table;
    private int currentByte;
    private int currentByteIndex;

    public HuffmanCompressOutput(PrefixCodesTable table) {
        this.table = table;
    }

    public void output() {
        outputTotalCount();
        output(table.getRoot());
        outputCompressedBytes();

        if (currentByteIndex > 0) {
            result.add(currentByte);
        }
    }

    private void outputTotalCount() {
        final int mask = 127;
        for (int numberFromRight = 3; numberFromRight >= 0; numberFromRight--) {
            int shift = numberFromRight*8;
            int value = (table.getTotalCountOfBytes() >> shift) & mask;
            result.add(value);
        }
    }

    private void output(HuffmanTreeNode node) {
        if (node.hasValue()) {
            writeSetBit();
            writeByte(node.getValue().getValue());
        }
        else {
            writeClearBit();
            output(node.getTop());
            output(node.getBottom());
        }
    }

    private void writeSetBit() {
        int mask = 128 >> currentByteIndex;
        currentByte |= mask;
        incrementIndexAndFlushByteIfNeed();
    }

    private void writeClearBit() {
        incrementIndexAndFlushByteIfNeed();
    }

    private void incrementIndexAndFlushByteIfNeed() {
        currentByteIndex++;
        if (currentByteIndex >= 8) {
            result.add(currentByte);
            currentByte = 0;
            currentByteIndex = 0;
        }
    }

    private void writeByte(int value) {
        int mask1 = (value >> currentByteIndex) & 255;
        currentByte |= mask1;

        result.add(currentByte);
        currentByte = 0;

        if (currentByteIndex > 0) {
            int mask2 = (value << (8 - currentByteIndex));
            mask2 &= 255;
            mask2 &= (255 << (8 - currentByteIndex));
            currentByte |= mask2;
        }
    }

    private void outputCompressedBytes() {
        for (HuffmanedByte huffmanedByte : table.compress()) {
            for (boolean bit : huffmanedByte.getCode()) {
                if (bit) {
                    writeSetBit();
                }
                else {
                    writeClearBit();
                }
            }
        }
    }
}
