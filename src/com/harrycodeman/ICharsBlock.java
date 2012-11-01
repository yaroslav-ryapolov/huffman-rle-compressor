package com.harrycodeman;

public interface ICharsBlock {
    boolean isSymbolSuitableForBlock(char s);
    char popLastSymbol() throws Exception;
    void addSymbol(char s) throws Exception;
    String getCompressedString();
    String getUncompressedString();
}
