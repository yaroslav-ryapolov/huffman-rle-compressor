package com.harrycodeman;

import java.util.Stack;

public class SameCharsBlock implements ICharsBlock {
    private char symbol;
    private int count;

    public SameCharsBlock(char symbol, int count) {
        this.symbol = symbol;
        this.count = count;
    }

    @Override
    public boolean isSymbolSuitableForBlock(char s) {
        return symbol == s;
    }

    @Override
    public void displaceUnsuitableSymbols(Stack<Character> stack) {
    }

    @Override
    public void addSymbol(char s) throws Exception {
        if (s != symbol) {
            throw new Exception("Different symbols in SameCharsBlock!");
        }
        count++;
    }

    @Override
    public String getCompressedString() {
        if (count > 0) {
            return "" + count + symbol;
        }
        return "";
    }

    @Override
    public String getUncompressedString() {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += symbol;
        }
        return result;
    }
}
