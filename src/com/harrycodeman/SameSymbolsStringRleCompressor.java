package com.harrycodeman;

public class SameSymbolsStringRleCompressor {
    private char[] symbols;
    private int sameSymbolsCounter = 0;

    public SameSymbolsStringRleCompressor(String toCompress) {
        symbols = toCompress.toCharArray();
    }

    public String compress() {
        String result = "";
        char p = symbols[0];
        for (char s : symbols) {
            if (s == p) {
                incrementSameSymbolsCounter();
            } else {
                result += prepareSameSymbolsCompressedBlock(p);
                p = s;
                resetSameSymbolsCounter();
            }
        }

        result += prepareSameSymbolsCompressedBlock(p);
        return result;
    }

    private String prepareSameSymbolsCompressedBlock(char symbol) {
        if (sameSymbolsCounter > 0) {
            return "" + sameSymbolsCounter + symbol;
        }
        return "";
    }

    private void resetSameSymbolsCounter() {
        sameSymbolsCounter = 1;
    }

    private void incrementSameSymbolsCounter() {
        sameSymbolsCounter++;
    }
}
