package com.harrycodeman;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StringRleCompressor {
    private List<ICharsBlock> blocks = new ArrayList<ICharsBlock>();
    private ICharsBlock currentSameBlock;
    private char[] symbols;

    private Stack<Character> stack = new Stack<Character>();

    public StringRleCompressor(String toCompress) {
        symbols = toCompress.toCharArray();
    }

    public String compress() throws Exception {
        for (char s : symbols) {
            AddSymbol(s);
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
            CreateSameCharsBlock(stack.pop());
        }
        else {
            CreateDifferentCharsBlock(stack.pop(), s);
        }
    }

    private void CreateSameCharsBlock(char s) throws Exception {
        currentSameBlock = new SameCharsBlock(s);
        currentSameBlock.addSymbol(s);
        currentSameBlock.addSymbol(s);
    }

    private void CreateDifferentCharsBlock(char s1, char s2) throws Exception {
        currentSameBlock = new DifferentCharsBlock();
        currentSameBlock.addSymbol(s1);
        currentSameBlock.addSymbol(s2);
    }

    private void AddSymbolToBlockOrFinishBlock(char s) throws Exception {
        if (currentSameBlock.isSymbolSuitableForBlock(s)) {
            currentSameBlock.addSymbol(s);
        } else {
            blocks.add(currentSameBlock);
            resetCounter(s);
        }
    }

    private void resetCounter(char newChar) throws Exception {
        currentSameBlock = null;
        stack.push(newChar);
    }

    private void flushStack() throws Exception {
        if (currentSameBlock == null
                && !stack.empty()) {
            currentSameBlock = new DifferentCharsBlock();
            currentSameBlock.addSymbol(stack.pop());
        }
        if (currentSameBlock != null) {
            blocks.add(currentSameBlock);
        }
    }

    private String getCompressedStringFromBlocks() {
        String result = "";
        for (ICharsBlock block : blocks) {
            result += block.getCompressedString();
        }
        return result;
    }
}
