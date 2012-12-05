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

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
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

    @Override
    public String toString() {
        return "[RGB: " + r + "; " + g + "; " + b + "]";
    }
}
