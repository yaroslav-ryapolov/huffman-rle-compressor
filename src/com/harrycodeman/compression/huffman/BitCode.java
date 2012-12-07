package com.harrycodeman.compression.huffman;

import java.util.Iterator;

public class BitCode implements Iterable<Boolean> {
    private class BitsIterator implements Iterator<Boolean> {
        private char[] valueArray = value.toCharArray();
        private int index;

        @Override
        public boolean hasNext() {
            return index < valueArray.length;
        }

        @Override
        public Boolean next() {
            return valueArray[index++] != '0';
        }

        @Override
        public void remove() {
        }
    }

    private String value = "";

    public BitCode() {
    }

    public BitCode(String value) {
        this.value = value;
    }

    public void appendToStart(String prefix) {
        value = prefix + value;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return new BitsIterator();
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == BitCode.class
                && equals((BitCode)other);
    }

    public boolean equals(BitCode other) {
        return value.equals(other.value);
    }

    @Override
    public String toString() {
        return value;
    }
}
