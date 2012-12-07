package com.harrycodeman.compression.rle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class DifferentBytesBlock extends BytesBlock {
    private List<Integer> symbols = new ArrayList<Integer>();

    public DifferentBytesBlock(String symbols) throws Exception {
        for (int s : symbols.toCharArray()) {
            this.symbols.add(s);
        }
        if (isBlockOverflowed()) {
            throw new Exception("Attempt to create overflowed block!");
        }
    }

    public DifferentBytesBlock(int s1, int s2) throws Exception {
        addSymbol(s1);
        addSymbol(s2);
    }

    @Override
    public boolean isSymbolSuitableForBlock(int s) {
        if (symbols.isEmpty()) {
            return true;
        }
        return s != getLastSymbol();
    }

    @Override
    public void displaceUnsuitableSymbols(Stack<Integer> stack) {
        stack.push(getLastSymbol());
        symbols.remove(symbols.size() - 1);
    }

    @Override
    public Iterator<Integer> compressedIterator() {
        List<Integer> result = new ArrayList<Integer>(symbols);
        byte sizeAsByte = (byte)(-size());
        result.add(0, (int)sizeAsByte);
        return result.iterator();
    }

    private int getLastSymbol() {
        return symbols.get(symbols.size() - 1);
    }

    @Override
    protected void addSymbolOverridden(int s) {
        symbols.add(s);
    }

    @Override
    public int size() {
        return symbols.size();
    }

    @Override
    public boolean equals(Object other) {
        if (other.getClass() != DifferentBytesBlock.class) {
            return false;
        }
        DifferentBytesBlock typedValue = (DifferentBytesBlock)other;
        return symbols.equals(typedValue.symbols);
    }

    @Override
    public Iterator<Integer> iterator() {
        return symbols.iterator();
    }
}
