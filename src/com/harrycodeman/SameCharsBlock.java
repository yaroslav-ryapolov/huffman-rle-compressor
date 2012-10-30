package com.harrycodeman;

public class SameCharsBlock implements ICharsBlock {
    private char symbol;
    private int count;

    public SameCharsBlock(char symbol) {
        this.symbol = symbol;
        count = 0;
    }

    public void addSymbol() {
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
