package com.harrycodeman;

import java.util.Stack;

public class DifferentCharsBlock implements ICharsBlock {
    private String symbols = "";

    public DifferentCharsBlock(char s1, char s2) {
        symbols = "" + s1 + s2;
    }

    @Override
    public boolean isSymbolSuitableForBlock(char s) {
        return s != getLastSymbol();
    }

    @Override
    public void displaceUnsuitableSymbols(Stack<Character> stack) {
        stack.push(getLastSymbol());
        symbols = symbols.substring(0, symbols.length() - 1);
    }

    private char getLastSymbol() {
        return symbols.charAt(symbols.length() - 1);
    }

    @Override
    public void addSymbol(char s) {
        symbols += s;
    }

    @Override
    public String getCompressedString() {
        return "-" + symbols.length() + symbols;
    }

    @Override
    public String getUncompressedString() {
        return symbols;
    }
}
