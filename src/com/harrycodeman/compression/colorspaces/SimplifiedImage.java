package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.List;

public class SimplifiedImage {
    private int height;
    private int width;

    public List<IPixelBlock> pixelBlocks = new ArrayList<IPixelBlock>();

    public SimplifiedImage(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == SimplifiedImage.class
                && equals((SimplifiedImage) other);
    }

    public boolean equals(SimplifiedImage other) {
        return height == other.height
                && width == other.width
                && pixelBlocks.equals(other.pixelBlocks);
    }
}
