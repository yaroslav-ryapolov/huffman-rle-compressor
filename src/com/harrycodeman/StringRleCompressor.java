package com.harrycodeman;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StringRleCompressor {
    private List<ICharsBlock> blocks = new ArrayList<ICharsBlock>();
    private ICharsBlock currentSameBlock;
    private Stack<Character> stack = new Stack<Character>();
    private ICompressCharStream stream;

    public StringRleCompressor(String toCompress) {
        stream = new StringCompressCharStream(toCompress);
    }

    public String compress() throws Exception {
        while (stream.canRead()) {
            AddSymbol(stream.getNextChar());
        }
        flushStack();
        return getCompressedStringFromBlocks();
    }

    private void AddSymbol(char s) throws Exception {
        if (currentSameBlock == null) {
            CreateNewBlockOrCollectStack(s);
        }
        else {
            AddSymbolToBlockOrFinishBlock(s);
        }
    }

    private void CreateNewBlockOrCollectStack(char s) throws Exception {
        if (stack.empty()) {
            stack.push(s);
        }
        else if (stack.peek() == s) {
            currentSameBlock = new SameCharsBlock(s, stack.size() + 1);
            stack.pop();
        }
        else {
            currentSameBlock = new DifferentCharsBlock(stack.pop(), s);
        }
    }

    private void AddSymbolToBlockOrFinishBlock(char s) throws Exception {
        if (currentSameBlock.isSymbolSuitableForBlock(s)) {
            currentSameBlock.addSymbol(s);
        } else {
            resetCounter(s);
        }
    }

    private void resetCounter(char newChar) throws Exception {
        currentSameBlock.displaceUnsuitableSymbols(stack);
        blocks.add(currentSameBlock);
        currentSameBlock = null;
        stack.push(newChar);
    }

    private void flushStack() throws Exception {
        if (currentSameBlock == null) {
            currentSameBlock = new SameCharsBlock(stack.pop(), 1);
        }
        blocks.add(currentSameBlock);
    }

    private String getCompressedStringFromBlocks() {
        String result = "";
        for (ICharsBlock block : blocks) {
            result += block.getCompressedString();
        }
        return result;
    }
}
