package com.harrycodeman;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SameSymbolsStringRleCompressor {
    private List<ICharsBlock> blocks = new ArrayList<ICharsBlock>();
    private ICharsBlock currentSameBlock;
    private char[] symbols;

    private Stack<Character> stack = new Stack<Character>();

    public SameSymbolsStringRleCompressor(String toCompress) {
        symbols = toCompress.toCharArray();
    }

    public String compress() throws Exception {
//        currentSameBlock = new SameCharsBlock(symbols[0]);

        for (char s : symbols) {
            AddChar(s);
        }

        if (currentSameBlock == null
                && !stack.empty()) {
            currentSameBlock = new CharBlockMock();
            currentSameBlock.addSymbol(stack.pop());
        }
        if (currentSameBlock != null) {
            blocks.add(currentSameBlock);
        }

        return getCompressedStringFromBlocks();
    }

    private void AddChar(char s) throws Exception {
        if (currentSameBlock == null) {
            if (stack.empty()) {
                stack.push(s);
            }
            else if (stack.peek() == s) {
                stack.pop();
                currentSameBlock = new SameCharsBlock(s);
                currentSameBlock.addSymbol(s);
                currentSameBlock.addSymbol(s);
            }
            else {
                currentSameBlock = new CharBlockMock();
                currentSameBlock.addSymbol(stack.pop());
                currentSameBlock.addSymbol(s);
            }
        }
        else {
            if (s == currentSameBlock.getLastSymbol()) {
                currentSameBlock.addSymbol(s);
            } else {
                blocks.add(currentSameBlock);
                resetCounter(s);
            }
        }
    }

    private void resetCounter(char newChar) throws Exception {
        currentSameBlock = null;
        stack.push(newChar);
    }

    private String getCompressedStringFromBlocks() {
        String result = "";
        for (ICharsBlock block : blocks) {
            result += block.getCompressedString();
        }
        return result;
    }
}
