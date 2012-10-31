package com.harrycodeman;

public interface ICharsBlock {
    char getLastSymbol();
    void addSymbol(char s) throws Exception;
    String getCompressedString();
    String getUncompressedString();
}
