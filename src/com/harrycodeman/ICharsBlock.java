package com.harrycodeman;

import java.util.Stack;

public interface ICharsBlock {
    boolean isSymbolSuitableForBlock(char s);
    void displaceUnsuitableSymbols(Stack<Character> stack);
    void addSymbol(char s) throws Exception;
    String getCompressedString();
    String getUncompressedString();
}
