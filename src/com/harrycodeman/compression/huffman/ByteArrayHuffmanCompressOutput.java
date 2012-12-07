package com.harrycodeman.compression.huffman;

import java.util.ArrayList;
import java.util.List;

public class ByteArrayHuffmanCompressOutput extends HuffmanCompressOutput {
    private List<Integer> result = new ArrayList<Integer>();

    public ByteArrayHuffmanCompressOutput(CompressPrefixCodesTable table) {
        super(table);
    }

    public List<Integer> getResult() {
        return result;
    }

    @Override
    protected void pushByte(int value) {
        result.add(value);
    }
}
