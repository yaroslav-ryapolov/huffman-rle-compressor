package com.harrycodeman;

import java.util.Stack;

public interface IBytesBlock {
    boolean isSymbolSuitableForBlock(int s);
    boolean isBlockFull();
    void displaceUnsuitableSymbols(Stack<Integer> stack);
    void addSymbol(int s) throws Exception;
    int size();
    String getCompressedString();
    String toHexString();
}
