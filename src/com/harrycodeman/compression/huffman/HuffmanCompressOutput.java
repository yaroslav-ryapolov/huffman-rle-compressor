package com.harrycodeman.compression.huffman;

import com.harrycodeman.compression.CompressOutput;

public abstract class HuffmanCompressOutput extends CompressOutput {
    private PrefixCodesTable table;

    public HuffmanCompressOutput(PrefixCodesTable table) {
        this.table = table;
    }

    public void output() {
        outputTotalCount();
        output(table.getRoot());
        outputCompressedBytes();
        flushCurrentByteIfNeed();
    }

    private void outputTotalCount() {
        final int mask = 127;
        for (int numberFromRight = 3; numberFromRight >= 0; numberFromRight--) {
            int shift = numberFromRight*8;
            int value = (table.getTotalCountOfBytes() >> shift) & mask;
            pushByte(value);
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
