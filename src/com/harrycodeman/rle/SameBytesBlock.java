package com.harrycodeman.rle;

import java.util.Iterator;

public class SameBytesBlock extends BytesBlock implements Iterable<Integer> {
    private class BlockIterator implements Iterator<Integer> {
        private int index = 0;

        public BlockIterator(SameBytesBlock block) {
        }

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
    public int size() {
        return count;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new BlockIterator(this);
    }

    @Override
    public boolean equals(Object value) {
        if (value.getClass() != SameBytesBlock.class) {
            return false;
        }
        SameBytesBlock typedValue = (SameBytesBlock)value;
        return size() == typedValue.size()
            && symbol == typedValue.symbol;
    }
}
