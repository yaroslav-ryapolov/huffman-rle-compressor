package com.harrycodeman;

public class SameSymbolsStringRleCompressor {
    private char[] symbols;
    private int sameSymbolsCounter = 0;
    private char previous;

    public SameSymbolsStringRleCompressor(String toCompress) {
        symbols = toCompress.toCharArray();
    }

    public String compress() {
        String result = "";
        previous = symbols[0];
        for (char s : symbols) {
            if (s == previous) {
                incrementCounter();
            } else {
                result += prepareCompressedBlock(previous);
                previous = s;
                resetCounter();
            }
        }
        result += prepareCompressedBlock(previous);
        return result;
    }

    private String prepareCompressedBlock(char symbol) {
        if (sameSymbolsCounter > 0) {
            return "" + sameSymbolsCounter + symbol;
        }
        return "";
    }

    private void resetCounter() {
        sameSymbolsCounter = 1;
    }

    private void incrementCounter() {
        sameSymbolsCounter++;
    }
}
