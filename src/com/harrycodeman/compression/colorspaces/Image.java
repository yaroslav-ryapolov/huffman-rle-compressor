package com.harrycodeman.compression.colorspaces;

import java.util.*;

import static java.lang.String.format;

public class Image implements Iterable<ThreeComponentPixelBlock> {
    private int height;
    private int width;
    private List<ThreeComponentPixelBlock> pixelBlocks;

    public Image(int width, int height, ThreeComponentPixelBlock... pixelBlocks) {
        this.width = width;
        this.height = height;
        this.pixelBlocks = new ArrayList<ThreeComponentPixelBlock>(width*height);
        Collections.addAll(this.pixelBlocks, pixelBlocks);
    }

    public Image(int width, int height, Iterable<ThreeComponentPixelBlock> pixelBlocks) {
        this(width, height);
        for (ThreeComponentPixelBlock b : pixelBlocks) {
            this.pixelBlocks.add(b);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidthAndHeight(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ThreeComponentPixelBlock get(int index) {
        return pixelBlocks.get(index);
    }

    public void set(int index, ThreeComponentPixelBlock value) {
        pixelBlocks.set(index, value);
    }

    public void add(ThreeComponentPixelBlock pixelBlock) {
        pixelBlocks.add(pixelBlock);
    }

    public void addAll(int index, Collection<ThreeComponentPixelBlock> pixelBlocks) {
        this.pixelBlocks.addAll(index, pixelBlocks);
    }

    public Collection<ThreeComponentPixelBlock> getRow(int index) {
        return pixelBlocks.subList(index*width, (index + 1)*width);
    }

    public int size() {
        return pixelBlocks.size();
    }

    public Iterable<ImagePart8x8> get8x8Parts() {
        final int imagePartWidth = (width % ImagePart8x8.WIDTH) == 0 ? (width / ImagePart8x8.WIDTH)
                : (width / ImagePart8x8.WIDTH) + 1;
        final int imagePartHeight = (height % ImagePart8x8.HEIGHT) == 0 ? (height / ImagePart8x8.HEIGHT)
                : (height / ImagePart8x8.HEIGHT) + 1;
        final int imagePartsCount = imagePartWidth * imagePartHeight;
        return new Iterable<ImagePart8x8>() {
            @Override
            public Iterator<ImagePart8x8> iterator() {
                return new Iterator<ImagePart8x8>() {
                    private int currentIndex = 0;

                    @Override
                    public boolean hasNext() {
                        return currentIndex < imagePartsCount;
                    }

                    @Override
                    public ImagePart8x8 next() {
                        return get8x8Part(currentIndex++);
                    }

                    @Override
                    public void remove() {
                    }
                };
            }
        };
    }

    public ImagePart8x8 get8x8Part(int index) {
        return new ImagePart8x8(width,  height, index, pixelBlocks);
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == Image.class
                && equals((Image) other);
    }

    public boolean equals(Image other) {
        return height == other.height
                && width == other.width
                && pixelBlocks.equals(other.pixelBlocks);
    }

    @Override
    public String toString() {
        String result = format("W: %01$d; H: %02$d; ", width, height);
        for (ThreeComponentPixelBlock b : pixelBlocks) {
            result += " " + b.toString();
        }
        return result;
    }

    @Override
    public Iterator<ThreeComponentPixelBlock> iterator() {
        return pixelBlocks.iterator();
    }
}
