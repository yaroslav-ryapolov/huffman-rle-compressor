package com.harrycodeman.rle;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MemoryCompressByteStream implements ICompressByteStream {
    private ByteArrayInputStream stream;

    public MemoryCompressByteStream(String symbols) {
        byte[] bytes = ConvertToByteArray(symbols.toCharArray());
        stream = new ByteArrayInputStream(bytes);
    }

    private byte[] ConvertToByteArray(char[] symbols) {
        byte[] result = new byte[symbols.length];
        for (int i = 0; i < symbols.length; i++) {
            result[i] = (byte)symbols[i];
        }
        return result;
    }

    @Override
    public boolean canRead() {
        return stream.available() > 0;
    }

    @Override
    public int getNextChar() {
        return stream.read();
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }
}
