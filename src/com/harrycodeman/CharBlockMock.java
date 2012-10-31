package com.harrycodeman;

public class CharBlockMock implements ICharsBlock {
    private String symbols = "";

    @Override
    public char getLastSymbol() {
        return symbols.toCharArray()[symbols.length() - 1];
    }

    @Override
    public void addSymbol(char s) {
        symbols += s;
    }

    @Override
    public String getCompressedString() {
        return symbols;
    }

    @Override
    public String getUncompressedString() {
        return symbols;
    }
}
