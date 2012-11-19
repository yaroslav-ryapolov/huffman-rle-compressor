package com.harrycodeman;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BytesRleCompressor {
    private List<IBytesBlock> blocks = new ArrayList<IBytesBlock>();
    private IBytesBlock currentBlock;
    private Stack<Integer> stack = new Stack<Integer>();
    private ICompressByteStream stream;

    public BytesRleCompressor(ICompressByteStream streamToCompress) {
        stream = streamToCompress;
    }

    public List<IBytesBlock> compress() throws Exception {
        while (stream.canRead()) {
            AddSymbol(stream.getNextChar());
        }
        stream.close();

        flushStack();
        return blocks;
    }

    private void AddSymbol(int s) throws Exception {
        if (currentBlock == null) {
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
            currentBlock = new SameBytesBlock(s, stack.size() + 1);
            stack.pop();
        }
        else {
            currentBlock = new DifferentBytesBlock(stack.pop(), s);
        }
    }

    private void AddSymbolToBlockOrFinishBlock(int s) throws Exception {
        if (currentBlock.isBlockFull()) {
            resetCounter(s);
        }
        else if (currentBlock.isSymbolSuitableForBlock(s)) {
            currentBlock.addSymbol(s);
        } else {
            currentBlock.displaceUnsuitableSymbols(stack);
            resetCounter(s);
        }
    }

    private void resetCounter(int newSymbol) throws Exception {
        blocks.add(currentBlock);
        currentBlock = null;
        stack.push(newSymbol);
    }

    private void flushStack() throws Exception {
        if (currentBlock == null) {
            currentBlock = new SameBytesBlock(stack.pop(), 1);
        }
        blocks.add(currentBlock);
    }

    private String getCompressedStringFromBlocks() {
        String result = "";
        for (IBytesBlock block : blocks) {
            result += block.getCompressedString();
        }
        return result;
    }
}
