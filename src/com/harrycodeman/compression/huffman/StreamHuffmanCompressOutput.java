package com.harrycodeman.compression.huffman;

import java.io.OutputStream;

public class StreamHuffmanCompressOutput extends HuffmanCompressOutput {
    private OutputStream output;

    public StreamHuffmanCompressOutput(OutputStream output, CompressPrefixCodesTable table) {
        super(table);
        this.output = output;
    }

    @Override
    protected void pushByte(int value) throws Exception {
        output.write(value);
    }
}
