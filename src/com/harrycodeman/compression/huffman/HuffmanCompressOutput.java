package com.harrycodeman.compression.huffman;

import com.harrycodeman.compression.CompressOutput;

public abstract class HuffmanCompressOutput extends CompressOutput {
    private CompressPrefixCodesTable table;

    public HuffmanCompressOutput(CompressPrefixCodesTable table) {
        this.table = table;
    }

    public void output() throws Exception {
        outputTotalCount();
        output(table.getRoot());
        outputCompressedBytes();
        flushCurrentByteIfNeed();
    }

    private void outputTotalCount() throws Exception {
        final int mask = 255;
        for (int numberFromRight = 3; numberFromRight >= 0; numberFromRight--) {
            int shift = numberFromRight*8;
            int value = (table.getTotalCountOfBytes() >> shift) & mask;
            pushByte(value);
        }
    }

    private void output(HuffmanTreeNode node) throws Exception {
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

    private void outputCompressedBytes() throws Exception {
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
