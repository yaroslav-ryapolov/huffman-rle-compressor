package com.harrycodeman;

public class DifferentSymbolsStringRleCompressor {
    private char[] symbols;
    private int counter = 1;
    private String sequence = "";

    public DifferentSymbolsStringRleCompressor(String toCompress) {
        symbols = toCompress.toCharArray();
    }

    public String compress() {
        String result = "";
        char p = symbols[0];
        boolean isStart = true;
        for (char s : symbols) {
            if (isStart) {
                isStart = false;
                resetCounter(s);
            } else {
                if (p != s) {
                    incrementCounter(s);
                    p = s;
                } else {
                    result += prepareCompressedBlock();
                    resetCounter(s);
                }
            }
        }
        return result + prepareCompressedBlock();
    }

    private String prepareCompressedBlock()
    {
        return "-" + counter + sequence;
    }

    private void resetCounter(char s) {
        counter = 1;
        sequence = "" + s;
    }

    private void incrementCounter(char s) {
        counter++;
        sequence += s;
    }
}
