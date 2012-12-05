package com.harrycodeman.compression.colorspaces;

public class YuvUToConstPixelBlockConverter implements IPixelBlockConverter {
    private int uValue;

    public YuvUToConstPixelBlockConverter(int uValue) {
        this.uValue = uValue;
    }

    @Override
    public IPixelBlock convert(IPixelBlock block) throws Exception {
        if (block.getClass() != Yuv444PixelBlock.class) {
            throw new Exception("Unsupported class try to convert!!!");
        }
        return convert((Yuv444PixelBlock)block);
    }

    public Yuv444PixelBlock convert(Yuv444PixelBlock block) {
        return new Yuv444PixelBlock(block.getY(), uValue, block.getV());
    }
}
