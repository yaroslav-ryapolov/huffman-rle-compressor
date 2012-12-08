package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.Collection;

public class YuvImageReader extends ImageReader {
    private int width;
    private int height;

    public YuvImageReader(int width, int height, String fileName) throws Exception {
        super(fileName);
        this.width = width;
        this.height = height;
    }

    @Override
    protected Image loadImageToImpl() throws Exception {
        return new Image(width, height, loadPixelBlocks());
    }

    private Collection<ThreeComponentPixelBlock> loadPixelBlocks() throws Exception {
        Collection<ThreeComponentPixelBlock> result = new ArrayList<ThreeComponentPixelBlock>();
        readByteIntoLastByte();
        while (isNotEof()) {
            readTwoPixelBlocks(result);
            readByteIntoLastByte();
        }
        return result;
    }

    private void readTwoPixelBlocks(Collection<ThreeComponentPixelBlock> pixelBlocks) throws Exception {
        int u = lastByte -128;
        int y1 = readByteIntoLastByte();
        int v = readByteIntoLastByte() - 128;
        int y2 = readByteIntoLastByte();
        pixelBlocks.add(new ThreeComponentPixelBlock(y1, u, v));
        pixelBlocks.add(new ThreeComponentPixelBlock(y2, u, v));
    }
}
