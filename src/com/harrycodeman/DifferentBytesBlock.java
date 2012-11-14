package com.harrycodeman;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DifferentBytesBlock implements IBytesBlock {
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
    public void addSymbol(int s) {
        symbols.add(s);
    }

    @Override
    public int size() {
        return symbols.size();
    }

    @Override
    public String getCompressedString() {
        String result = "";
        for (int s : symbols) {
            result += getSymbolChar(s);
        }
        return "-" + symbols.size() + result;
    }

    private char getSymbolChar(int s) {
        return (char)s;
    }

    @Override
    public String toHexString() {
        String hex = getSymbolsAsHex();
        return "(-" + symbols.size() + ") " + hex + "||| " + hex + "\n";
    }

    private String getSymbolsAsHex() {
        String hex = "";
        for (int s : symbols) {
            hex += ByteToHexStringConvertor.Convert(s) + " ";
        }
        return hex;
    }
}
