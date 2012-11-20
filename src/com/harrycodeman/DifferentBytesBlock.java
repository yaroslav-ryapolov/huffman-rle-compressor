package com.harrycodeman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DifferentBytesBlock extends BytesBlock implements ICompressByteStream {
    private List<Integer> symbols = new ArrayList<Integer>();

    public DifferentBytesBlock(int s1, int s2) {
        symbols.add(s1);
        symbols.add(s2);
    }

    @Override
    public boolean isSymbolSuitableForBlock(int s) {
        return s != getLastSymbol();
    }

    @Override
    public void displaceUnsuitableSymbols(Stack<Integer> stack) {
        stack.push(getLastSymbol());
        symbols.remove(symbols.size() - 1);
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
    public String toHexString() {
        String hex = getSymbolsAsHex();
        return "(-" + symbols.size() + ") " + hex + "||| " + hex + "\n";
    }

    private String getSymbolsAsHex() {
        String hex = "";
        for (int s : symbols) {
            hex += ToHexStringConverter.convertByte(s) + " ";
        }
        return hex;
    }

    private int streamIndex = 0;

    @Override
    public boolean canRead() {
        return streamIndex < size();
    }

    @Override
    public int getNextChar() {
        return symbols.get(streamIndex++);
    }

    @Override
    public void close() throws IOException {
        streamIndex = 0;
    }
}
