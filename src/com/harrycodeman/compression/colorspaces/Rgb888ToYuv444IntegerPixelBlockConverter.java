package com.harrycodeman.compression.colorspaces;

public class Rgb888ToYuv444IntegerPixelBlockConverter implements IPixelBlockConverter {
    private boolean isShiftCoefficients;

    public Rgb888ToYuv444IntegerPixelBlockConverter(boolean isShiftCoefficients) {
        this.isShiftCoefficients = isShiftCoefficients;
    }

    @Override
    public IPixelBlock convert(IPixelBlock block) throws Exception {
        if (block.getClass() != Rgb888PixelBlock.class) {
            throw new Exception("Unsupported class try to convert!!!");
        }
        return convert((Rgb888PixelBlock)block);
    }

    public Yuv444PixelBlock convert(Rgb888PixelBlock block) {
        Yuv444PixelBlock yuvBlock = doBaseTransformation(block);
        yuvBlock = scaleCoefficientsDown(yuvBlock);
        return shiftCoefficients(yuvBlock);
    }

    private Yuv444PixelBlock doBaseTransformation(Rgb888PixelBlock block) {
        int y = 66*block.getR() + 129*block.getG() + 25*block.getB();
        int u = -37*block.getR() - 74*block.getG() + 112*block.getB();
        int v = 112*block.getR() - 94*block.getG() - 18*block.getB();
        return new Yuv444PixelBlock(y, u, v);
    }

    private Yuv444PixelBlock scaleCoefficientsDown(Yuv444PixelBlock block) {
        int y = (block.getY() + 128) >> 8;
        int u = (block.getU() + 128) >> 8;
        int v = (block.getV() + 128) >> 8;
        return new Yuv444PixelBlock(y, u, v);
    }

    private Yuv444PixelBlock shiftCoefficients(Yuv444PixelBlock block) {
        if (isShiftCoefficients) {
            int y = block.getY() + 16;
            int u = block.getU() + 128;
            int v = block.getV() + 128;
            return new Yuv444PixelBlock(y, u, v);
        }
        return block;
    }
}
