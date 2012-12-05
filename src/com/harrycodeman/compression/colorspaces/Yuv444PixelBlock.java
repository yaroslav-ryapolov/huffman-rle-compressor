package com.harrycodeman.compression.colorspaces;

public class Yuv444PixelBlock implements IPixelBlock {
    private int y;
    private int u;
    private int v;

    public Yuv444PixelBlock(int y, int u, int v) {
        this.y = y;
        this.u = u;
        this.v = v;
    }

    public int getY() {
        return y;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == Yuv444PixelBlock.class
                && equals((Yuv444PixelBlock)other);
    }

    public boolean equals(Yuv444PixelBlock other) {
        return y == other.y
                && u == other.u
                && v == other.v;
    }

    @Override
    public String toString() {
        return "[YUV444: " + y + "; " + u + "; " + v + "]";
    }
}
