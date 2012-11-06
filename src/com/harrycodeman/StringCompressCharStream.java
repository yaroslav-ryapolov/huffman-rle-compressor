package com.harrycodeman;

public class StringCompressCharStream implements ICompressCharStream {
    private char[] symbols;
    private int counter = 0;

    public StringCompressCharStream(String symbols) {
        this.symbols = symbols.toCharArray();
    }

    @Override
    public boolean canRead() {
        return counter < symbols.length;
    }

    @Override
    public char getNextChar() {
        return symbols[counter++];
    }
}
