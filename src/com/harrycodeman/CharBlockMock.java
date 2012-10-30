package com.harrycodeman;

public class CharBlockMock implements ICharsBlock {
    private String symbols = "";

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
