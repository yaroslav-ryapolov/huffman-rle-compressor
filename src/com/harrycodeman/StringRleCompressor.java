package com.harrycodeman;

public class StringRleCompressor {
    private char[] symbols;
    private int counter = 0;

    public StringRleCompressor(String toCompress) {
        symbols = toCompress.toCharArray();
    }

    public String compress() {
        String result = "";
        char p = symbols[0];
        for (char s : symbols) {
            if (s == p) {
                incrementCounter();
            } else {
                result += prepareCompressedBlock(p);
                p = s;
                resetCounter();
            }
        }

        result += prepareCompressedBlock(p);
        return result;
    }

    private String prepareCompressedBlock(char symbol) {
        if (counter > 1) {
            return "" + counter + symbol;

        }
        return "" + symbol;
    }

    private void resetCounter() {
        counter = 1;
    }

    private void incrementCounter() {
        counter++;
    }
}
