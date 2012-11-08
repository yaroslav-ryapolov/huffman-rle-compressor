package com.harrycodeman;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StringRleCompressor {
    private List<IBytesBlock> blocks = new ArrayList<IBytesBlock>();
    private IBytesBlock currentSameBlock;
    private Stack<Integer> stack = new Stack<Integer>();
    private ICompressByteStream stream;

    public StringRleCompressor(String toCompress) {
        stream = new StringCompressByteStream(toCompress);
    }

    public String compress() throws Exception {
        while (stream.canRead()) {
            AddSymbol(stream.getNextChar());
        }
        stream.close();

        flushStack();
        return getCompressedStringFromBlocks();
    }

    private void AddSymbol(int s) throws Exception {
        if (currentSameBlock == null) {
            CreateNewBlockOrCollectStack(s);
        }
        else {
            AddSymbolToBlockOrFinishBlock(s);
        }
    }

    private void CreateNewBlockOrCollectStack(int s) throws Exception {
        if (stack.empty()) {
            stack.push(s);
        }
        else if (stack.peek() == s) {
            currentSameBlock = new SameBytesBlock(s, stack.size() + 1);
            stack.pop();
        }
        else {
            currentSameBlock = new DifferentBytesBlock(stack.pop(), s);
        }
    }

    private void AddSymbolToBlockOrFinishBlock(int s) throws Exception {
        if (currentSameBlock.isSymbolSuitableForBlock(s)) {
            currentSameBlock.addSymbol(s);
        } else {
            resetCounter(s);
        }
    }

    private void resetCounter(int newSymbol) throws Exception {
        currentSameBlock.displaceUnsuitableSymbols(stack);
        blocks.add(currentSameBlock);
        currentSameBlock = null;
        stack.push(newSymbol);
    }

    private void flushStack() throws Exception {
        if (currentSameBlock == null) {
            currentSameBlock = new SameBytesBlock(stack.pop(), 1);
        }
        blocks.add(currentSameBlock);
    }

    private String getCompressedStringFromBlocks() {
        String result = "";
        for (IBytesBlock block : blocks) {
            result += block.getCompressedString();
        }
        return result;
    }
}
