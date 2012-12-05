package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SimplifiedImage implements Collection<IPixelBlock> {
    private int height;
    private int width;
    private List<IPixelBlock> pixelBlocks = new ArrayList<IPixelBlock>();

    public SimplifiedImage(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public SimplifiedImage(int width, int height, Collection<IPixelBlock> pixelBlocks) {
        this.width = width;
        this.height = height;
        this.pixelBlocks = new ArrayList<IPixelBlock>(pixelBlocks);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SimplifiedImage convert(IPixelBlockConverter converter) throws Exception {
        List<IPixelBlock> convertedBlocks = new ArrayList<IPixelBlock>(pixelBlocks.size());
        for (IPixelBlock b : pixelBlocks) {
            convertedBlocks.add(converter.convert(b));
        }
        return new SimplifiedImage(width,  height, convertedBlocks);
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
    public Iterator<IPixelBlock> iterator() {
        return pixelBlocks.iterator();
    }

    @Override
    public Object[] toArray() {
        return pixelBlocks.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return  pixelBlocks.toArray(a);
    }

    @Override
    public boolean add(IPixelBlock pixelBlock) {
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
    public boolean addAll(Collection<? extends IPixelBlock> c) {
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
