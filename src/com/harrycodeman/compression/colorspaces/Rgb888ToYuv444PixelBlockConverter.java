package com.harrycodeman.compression.colorspaces;

public class Rgb888ToYuv444PixelBlockConverter implements IPixelBlockConverter {
    @Override
    public IPixelBlock convert(IPixelBlock block) throws Exception {
        if (block.getClass() != Rgb888PixelBlock.class) {
            throw new Exception("Unsupported class try to convert!!!");
        }
        return convert((Rgb888PixelBlock)block);
    }

    public static Yuv444PixelBlock convert(Rgb888PixelBlock block) {
        int y = (int)(0.299*block.getR() + 0.587*block.getG() + 0.114*block.getB());
        int u = (int)(-0.14713*block.getR() - 0.28886*block.getG() + 0.436*block.getB());
        int v = (int)(0.615*block.getR() - 0.51499*block.getG() - 0.10001*block.getB());
        return new Yuv444PixelBlock(y, u, v);
    }
}
