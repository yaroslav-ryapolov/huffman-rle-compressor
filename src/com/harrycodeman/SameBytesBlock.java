package com.harrycodeman;

import java.util.Stack;

public class SameBytesBlock implements IBytesBlock {
    private int symbol;
    private int count;

    public SameBytesBlock(int symbol, int count) {
        this.symbol = symbol;
        this.count = count;
    }

    @Override
    public boolean isSymbolSuitableForBlock(int s) {
        return symbol == s;
    }

    @Override
    public void displaceUnsuitableSymbols(Stack<Integer> stack) {
    }

    @Override
    public void addSymbol(int s) throws Exception {
        if (s != symbol) {
            throw new Exception("Different symbols in SameBytesBlock!");
        }
        count++;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String getCompressedString() {
        if (count > 0) {
            return "" + count + getSymbolChar();
        }
        return "";
    }

    private char getSymbolChar() {
        return (char)symbol;
    }

    @Override
    public String toHexString() {
        String hex = ByteToHexStringConvertor.Convert(symbol);
        return "(" + count + ") " + hex + " ||| " + GetUncompressedString(hex) + "\n";
    }

    private String GetUncompressedString(String hex) {
        String uncompressed = "";
        for (int i = 0; i < count; i++) {
            uncompressed += hex + " ";
        }
        return uncompressed;
    }
}
