package com.harrycodeman.compression.colorspaces;

public class YuvVToConstPixelBlockConverter implements IPixelBlockConverter {
    private int vValue;

    public YuvVToConstPixelBlockConverter(int vValue) {
        this.vValue = vValue;
    }

    @Override
    public IPixelBlock convert(IPixelBlock block) throws Exception {
        if (block.getClass() != Yuv444PixelBlock.class) {
            throw new Exception("Unsupported class try to convert!!!");
        }
        return convert((Yuv444PixelBlock)block);
    }

    public Yuv444PixelBlock convert(Yuv444PixelBlock block) {
        return new Yuv444PixelBlock(block.getY(), block.getU(), vValue);
    }
}
