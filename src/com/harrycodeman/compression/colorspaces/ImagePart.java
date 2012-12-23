package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

// TODO: introduce ImagePart
public class ImagePart implements IImagePart {
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    public static final ThreeComponentPixelBlock EMPTY = new ThreeComponentPixelBlock(0, 0, 0);

    private final int imageWidth;
    private final int imageHeight;
    private final int xIndex;
    private final int yIndex;
    private final List<ThreeComponentPixelBlock> pixelBlocks;

    public ImagePart(ThreeComponentPixelBlock... pixelBlocks) {
        imageWidth = WIDTH;
        imageHeight = HEIGHT;
        xIndex = 0;
        yIndex = 0;
        this.pixelBlocks = new ArrayList<ThreeComponentPixelBlock>(pixelBlocks.length);
        addAll(this.pixelBlocks, pixelBlocks);
    }

    public ImagePart(int imageWidth, int imageHeight, int index, List<ThreeComponentPixelBlock> pixelBlocks) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.xIndex = getHorizontalShift(imageWidth, index);
        this.yIndex = getVerticalShift(imageWidth, index);
        this.pixelBlocks = pixelBlocks;
    }

    private int getHorizontalShift(int imageWidth, int index) {
        int imagePartWidth = imageWidth%WIDTH == 0 ? imageWidth/WIDTH : imageWidth/WIDTH + 1;
        return (index % imagePartWidth) * WIDTH;
    }

    private int getVerticalShift(int imageWidth, int index) {
        int imagePartWidth = imageWidth%WIDTH == 0 ? imageWidth/WIDTH : imageWidth/WIDTH + 1;
        return (index / imagePartWidth) * HEIGHT;
    }

    public ThreeComponentPixelBlock get(int x, int y) {
        int shiftedX = x + xIndex;
        int shiftedY = y + yIndex;
        if (shiftedX < imageWidth
                && shiftedY < imageHeight) {
            return pixelBlocks.get(shiftedY*imageWidth + shiftedX);
        }
        return EMPTY;
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == ImagePart.class
                && equals((ImagePart)other);
    }

    public boolean equals(ImagePart other) {
        for (int i = 0; i < ImagePart.WIDTH; i++) {
            for (int j = 0; j < ImagePart.HEIGHT; j++) {
                if (!get(i, j).equals(other.get(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "";
        for (int j = 0; j < ImagePart.HEIGHT; j++) {
            for (int i = 0; i < ImagePart.WIDTH; i++) {
                result += get(i, j);
            }
            result += "\n";
        }
        return result;
    }
}
