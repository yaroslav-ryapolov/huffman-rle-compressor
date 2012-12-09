package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.Collection;

public class PpmImageReader extends ImageReader {
    public PpmImageReader(String fileName) throws Exception {
        super(fileName);
    }

    @Override
    protected Image loadImageToImpl() throws Exception {
        readFormatHeader();
        int width = readNextWordAsInt();
        int height = readNextWordAsInt();
        readColorLimitAndSpaces();
        return new Image(width, height, readPixelBlocks(width*height));
    }

    private void readFormatHeader() throws Exception {
        String formatHeader = readNextWord();
        if (formatHeader == null
                || !formatHeader.equals("P6")) {
            throw new Exception("Unsupported file format!!!");
        }
    }

    private void readColorLimitAndSpaces() throws Exception {
        if (readNextWordAsInt() != 255) {
            throw new Exception("Unsupported file format!!!");
        }
        while (lastByte != '\n') {
            readByteAsInt();
        }
    }

    private Collection<ThreeComponentPixelBlock> readPixelBlocks(int size) throws Exception {
        Collection<ThreeComponentPixelBlock> result = new ArrayList<ThreeComponentPixelBlock>(size);
        for (int i = 0; i < size; i++) {
            result.add(new ThreeComponentPixelBlock(readByteAsInt(), readByteAsInt(), readByteAsInt()));
        }
        return result;
    }
}
