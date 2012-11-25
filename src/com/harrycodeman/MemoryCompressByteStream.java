package com.harrycodeman;

import com.harrycodeman.ICompressByteStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

public class MemoryCompressByteStream implements ICompressByteStream {
    private class StreamIterator implements Iterator<Integer> {
        private int index = 0;

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

    public MemoryCompressByteStream(byte[] bytes) {
        stream = new ByteArrayInputStream(bytes);
    }

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
    public Iterator<Integer> iterator() {
        return new StreamIterator();
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }
}
