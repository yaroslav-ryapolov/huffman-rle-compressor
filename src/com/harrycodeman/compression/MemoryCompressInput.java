package com.harrycodeman.compression;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

public class MemoryCompressInput implements ICompressInput {
    private class StreamIterator implements Iterator<Integer> {
        @Override
        public boolean hasNext() {
            return stream.available() > 0;
        }

        @Override
        public Integer next() {
            return stream.read();
        }

        @Override
        public void remove() {
        }
    }

    private ByteArrayInputStream stream;

    public MemoryCompressInput(byte[] bytes) {
        stream = new ByteArrayInputStream(bytes);
    }

    public MemoryCompressInput(String symbols) {
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
    public Iterator<Integer> iterator() {
        return new StreamIterator();
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }

    @Override
    public void reset(){
        stream.reset();
    }
}
