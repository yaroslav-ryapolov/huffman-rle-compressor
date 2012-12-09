package com.harrycodeman.compression.colorspaces;

import java.util.*;

import static java.lang.String.format;

public class Image implements Collection<ThreeComponentPixelBlock> {
    private int height;
    private int width;
    private List<ThreeComponentPixelBlock> pixelBlocks;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        pixelBlocks = new ArrayList<ThreeComponentPixelBlock>(width*height);
    }

    public Image(int width, int height, ThreeComponentPixelBlock... pixelBlocks) {
        this(width, height);
        Collections.addAll(this.pixelBlocks, pixelBlocks);
    }

    public Image(int width, int height, Collection<ThreeComponentPixelBlock> pixelBlocks) {
        this(width, height);
        this.pixelBlocks.addAll(pixelBlocks);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Collection<ThreeComponentPixelBlock> getRowBlocks(int rowIndex) {
        return pixelBlocks.subList(rowIndex*width, rowIndex*width + width);
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
    public int size() {
        return pixelBlocks.size();
    }

    @Override
    public boolean isEmpty() {
        return pixelBlocks.isEmpty();
    }

    @Override
    public boolean contains(Object other) {
        return pixelBlocks.contains(other);
    }

    @Override
    public Iterator<ThreeComponentPixelBlock> iterator() {
        return pixelBlocks.iterator();
    }

    @Override
    public Object[] toArray() {
        return pixelBlocks.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return pixelBlocks.toArray(a);
    }

    @Override
    public boolean add(ThreeComponentPixelBlock pixelBlock) {
        return pixelBlocks.add(pixelBlock);
    }

    @Override
    public boolean remove(Object other) {
        return pixelBlocks.remove(other);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return pixelBlocks.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends ThreeComponentPixelBlock> c) {
        return pixelBlocks.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return pixelBlocks.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return pixelBlocks.retainAll(c);
    }

    @Override
    public void clear() {
        pixelBlocks.clear();
    }
}
