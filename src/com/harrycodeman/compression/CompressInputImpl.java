package com.harrycodeman.compression;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class CompressInputImpl implements ICompressInput {
    private class StreamIterator implements Iterator<Integer> {
        @Override
        public boolean hasNext() {
            try {
                return stream.available() > 0;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public Integer next() {
            try {
                return stream.read();
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        }

        @Override
        public void remove() {
        }
    }

    private InputStream stream;

    public CompressInputImpl(List<Integer> bytesList) {
        byte[] bytes = ConvertToByteArray(bytesList);
        stream = new ByteArrayInputStream(bytes);
    }

    private byte[] ConvertToByteArray(List<Integer> bytesList) {
        byte[] result = new byte[bytesList.size()];
        for (int i = 0; i < bytesList.size(); i++) {
            result[i] = bytesList.get(i).byteValue();
        }
        return result;
    }

    public CompressInputImpl(byte[] bytes) {
        stream = new ByteArrayInputStream(bytes);
    }

    public CompressInputImpl(String symbols) {
        byte[] bytes = ConvertToByteArray(symbols.toCharArray());
        stream = new ByteArrayInputStream(bytes);
    }

    public CompressInputImpl(InputStream stream) {
        this.stream = stream;
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
    public void reset() throws IOException {
        stream.reset();
    }
}
