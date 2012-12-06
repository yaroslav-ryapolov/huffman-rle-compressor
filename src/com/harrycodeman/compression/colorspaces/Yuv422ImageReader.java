package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.Collection;

public class Yuv422ImageReader extends ImageReader {
    private int width;
    private int height;

    public Yuv422ImageReader(int width, int height, String fileName) throws Exception {
        super(fileName);
        this.width = width;
        this.height = height;
    }

    @Override
    protected SimplifiedImage loadImageToImpl() throws Exception {
        return new SimplifiedImage(width, height, loadPixelBlocks());
    }

    private Collection<IPixelBlock> loadPixelBlocks() throws Exception {
        Collection<IPixelBlock> result = new ArrayList<IPixelBlock>();
        readByteIntoLastByte();
        while (isNotEof()) {
            readTwoPixelBlocks(result);
            readByteIntoLastByte();
        }
        return result;
    }

    private void readTwoPixelBlocks(Collection<IPixelBlock> pixelBlocks) throws Exception {
        int u = lastByte -128;
        int y1 = readByteIntoLastByte();
        int v = readByteIntoLastByte() - 128;
        int y2 = readByteIntoLastByte();
        pixelBlocks.add(new Yuv444PixelBlock(y1, u, v));
        pixelBlocks.add(new Yuv444PixelBlock(y2, u, v));
    }
}
