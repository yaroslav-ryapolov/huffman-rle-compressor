package com.harrycodeman.compression.colorspaces;

public class YuvYToConstPixelBlockConverter implements IPixelBlockConverter {
    private int yValue;

    public YuvYToConstPixelBlockConverter(int yValue) {
        this.yValue = yValue;
    }

    @Override
    public IPixelBlock convert(IPixelBlock block) throws Exception {
        if (block.getClass() != Yuv444PixelBlock.class) {
            throw new Exception("Unsupported class try to convert!!!");
        }
        return convert((Yuv444PixelBlock)block);
    }

    public Yuv444PixelBlock convert(Yuv444PixelBlock block) {
        return new Yuv444PixelBlock(yValue, block.getU(), block.getV());
    }
}
