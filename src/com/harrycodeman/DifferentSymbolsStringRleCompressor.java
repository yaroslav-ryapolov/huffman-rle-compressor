package com.harrycodeman;

public class DifferentSymbolsStringRleCompressor {
    private String symbols;

    public DifferentSymbolsStringRleCompressor(String toCompress) {
        symbols = toCompress;
    }

    public String compress() {
        return "-" + symbols.length() + symbols;
    }
}
