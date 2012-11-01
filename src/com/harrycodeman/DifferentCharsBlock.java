package com.harrycodeman;

public class DifferentCharsBlock implements ICharsBlock {
    private String symbols = "";

    @Override
    public boolean isSymbolSuitableForBlock(char s) {
        return s != getLastSymbol();
    }

    @Override
    public char popLastSymbol() throws Exception {
        if (symbols.length() == 0) {
            throw new Exception("Attempt to pop from empty block");
        }

        char result = getLastSymbol();
        symbols = symbols.substring(0, symbols.length());
        return result;
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
