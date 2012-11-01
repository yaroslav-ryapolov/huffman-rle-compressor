package com.harrycodeman;

public class SameCharsBlock implements ICharsBlock {
    private char symbol;
    private int count;

    public SameCharsBlock(char symbol) {
        this.symbol = symbol;
        count = 0;
    }

    @Override
    public boolean isSymbolSuitableForBlock(char s) {
        return symbol == s;
    }

    @Override
    public char popLastSymbol() throws Exception {
        if (count == 0) {
            throw new Exception("Attempt to pop from empty block");
        }

        count--;
        return symbol;
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
