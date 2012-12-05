package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.Collection;

public class PpmImageReader extends ImageReader {
    public PpmImageReader(String filePath) throws Exception {
        super(filePath);
    }

    @Override
    protected SimplifiedImage loadImageToImpl() throws Exception {
        readFormatHeader();
        int width = readNextWordAsInt();
        int height = readNextWordAsInt();
        readColorLimitAndSpaces();
        return new SimplifiedImage(width, height, readPixelBlocks(width*height));
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
            readByteIntoLastByte();
        }
    }

    private Collection<IPixelBlock> readPixelBlocks(int size) throws Exception {
        Collection<IPixelBlock> result = new ArrayList<IPixelBlock>(size);
        for (int i = 0; i < size; i++) {
            result.add(new Rgb888PixelBlock(readByteIntoLastByte(), readByteIntoLastByte(), readByteIntoLastByte()));
        }
        return result;
    }
}
