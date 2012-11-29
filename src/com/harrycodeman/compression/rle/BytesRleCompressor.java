package com.harrycodeman.compression.rle;

import com.harrycodeman.compression.ICompressInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BytesRleCompressor {
    private List<BytesBlock> blocks = new ArrayList<BytesBlock>();
    private Stack<Integer> stack = new Stack<Integer>();
    private BytesBlock currentBlock;
    private ICompressInput stream;

    public BytesRleCompressor(ICompressInput streamToCompress) {
        stream = streamToCompress;
    }

    public List<BytesBlock> compress() throws Exception {
        for (int s : stream) {
            addSymbol(s);
        }
        stream.close();
        flushStack();
        return blocks;
    }

    private void addSymbol(int s) throws Exception {
        if (currentBlock == null) {
            createNewBlockOrCollectStack(s);
        }
        else {
            addSymbolToBlockOrFinishBlock(s);
        }
    }

    private void createNewBlockOrCollectStack(int s) throws Exception {
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

    private void addSymbolToBlockOrFinishBlock(int s) throws Exception {
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
}
