package com.harrycodeman.compression.colorspaces;

public class Yuv444ToRgb888PixelBlockConverter implements IPixelBlockConverter {
    @Override
    public IPixelBlock convert(IPixelBlock block) throws Exception {
        if (block.getClass() != Yuv444PixelBlock.class) {
            throw new Exception("Unsupported class try to convert!!!");
        }
        return convert((Yuv444PixelBlock)block);
    }

    public static Rgb888PixelBlock convert(Yuv444PixelBlock block) {
        int r = clamp(block.getY() + 1.13983*block.getV());
        int g = clamp(block.getY() - 0.39465*block.getU() - 0.58060*block.getV());
        int b = clamp(block.getY() + 2.03211*block.getU());
        return new Rgb888PixelBlock(r, g, b);
    }

    private static int clamp(double value) {
        int intValue = (int)value;
        return intValue > 255 ? 255 : intValue < 0 ? 0 : intValue;
    }
}
