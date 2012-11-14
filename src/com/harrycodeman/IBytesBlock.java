package com.harrycodeman;

import java.util.Stack;

public interface IBytesBlock {
    boolean isSymbolSuitableForBlock(int s);
    void displaceUnsuitableSymbols(Stack<Integer> stack);
    void addSymbol(int s) throws Exception;
    String getCompressedString();
    String toHexString();
}
