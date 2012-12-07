package com.harrycodeman.compression.rle;

import java.util.Iterator;

public class SameBytesBlock extends BytesBlock implements Iterable<Integer> {
    private class CompressedBlockIterator implements Iterator<Integer> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < 2;
        }

        @Override
        public Integer next() {
            if (index == 0) {
                index++;
                return size() - 1;
            }
            index++;
            return symbol;
        }

        @Override
        public void remove() {
        }
    }

    private class BlockIterator implements Iterator<Integer> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public Integer next() {
            index++;
            return symbol;
        }

        @Override
        public void remove() {
        }
    }

    private int symbol;
    private int count;

    public SameBytesBlock(int symbol, int count) throws Exception {
        this.symbol = symbol;
        this.count = count;
        if (isBlockOverflowed()) {
            throw new Exception("Attempt to create overflowed block!");
        }
    }

    public int getSymbol() {
        return symbol;
    }

    @Override
    public boolean isSymbolSuitableForBlock(int s) {
        return symbol == s;
    }

    @Override
    protected void addSymbolOverridden(int s) {
        count++;
    }

    @Override
    public Iterator<Integer> compressedIterator() {
        return new CompressedBlockIterator();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new BlockIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other.getClass() != SameBytesBlock.class) {
            return false;
        }
        SameBytesBlock typedValue = (SameBytesBlock)other;
        return size() == typedValue.size()
            && symbol == typedValue.symbol;
    }
}
