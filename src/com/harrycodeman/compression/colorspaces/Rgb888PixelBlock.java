package com.harrycodeman.compression.colorspaces;

public class Rgb888PixelBlock implements IPixelBlock {
    private int r;
    private int g;
    private int b;

    public Rgb888PixelBlock(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Yuv444PixelBlock convertToYuv444() {
        int y = (int)(0.299*r + 0.587*g + 0.114*b);
        int u = (int)(-0.14713*r - 0.28886*g + 0.436*b);
        int v = (int)(0.615*r - 0.51499*g - 0.10001*b);
        return new Yuv444PixelBlock(y, u, v);
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == Rgb888PixelBlock.class
                && equals((Rgb888PixelBlock)other);
    }

    public boolean equals(Rgb888PixelBlock other) {
        return r == other.r
                && g == other.g
                && b == other.b;
    }
}
