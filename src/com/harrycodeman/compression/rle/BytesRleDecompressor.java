package com.harrycodeman.compression.rle;

import com.harrycodeman.compression.ICompressInput;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BytesRleDecompressor {
    private ICompressInput input;

    public BytesRleDecompressor(ICompressInput input) {
        this.input = input;
    }

    public List<Integer> decompress() {
        List<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iterator = input.iterator();
        while (iterator.hasNext()) {
            int blockSize = iterator.next();
            if (blockSize > 127) {
                for (int i = 0; i < blockSize - 127; i++) {
                    result.add(iterator.next());
                }
            }
            else {
                int symbol = iterator.next();
                for (int i = 0; i < blockSize + 1; i++) {
                    result.add(symbol);
                }
            }
        }
        return result;
    }
}
