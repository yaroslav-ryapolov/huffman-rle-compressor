package com.harrycodeman.compression.rle;

import com.harrycodeman.compression.ICompressInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BytesBlockCompressInput implements ICompressInput {
    private class CompressInputIterator implements Iterator<Integer> {
        private Iterator<BytesBlock> blockIterator = blocks.iterator();
        private Iterator<Integer> byteIterator;

        @Override
        public boolean hasNext() {
            return (byteIterator != null
                            && byteIterator.hasNext())
                    ||  blockIterator.hasNext();
        }

        @Override
        public Integer next() {
            if (byteIterator == null
                    || !byteIterator.hasNext()) {
                byteIterator = blockIterator.next().iterator();
            }
            return byteIterator.next();
        }

        @Override
        public void remove() {
        }
    }

    private List<BytesBlock> blocks;

    public BytesBlockCompressInput(List<BytesBlock> blocks) {
        this.blocks = blocks;
    }

    public BytesBlockCompressInput(BytesBlock[] blocks) {
        this.blocks = new ArrayList<BytesBlock>();
        Collections.addAll(this.blocks, blocks);
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public void reset() {
    }

    @Override
    public Iterator<Integer> iterator() {
        return new CompressInputIterator();
    }
}
