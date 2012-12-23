package com.harrycodeman.compression.colorspaces;

import java.util.*;

import static java.lang.String.format;

public class Image implements Iterable<ThreeComponentPixelBlock> {
    // TODO: introduce rows
    // TODO: introduce columns
    // TODO: introduce matrix

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

    public ImagePart get8x8Part(int index) {
        return new ImagePart(width, height, index, pixelBlocks);
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
