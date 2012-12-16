package com.harrycodeman.compression.colorspaces;

import java.util.*;

import static java.lang.Math.min;
import static java.lang.String.format;
import static java.util.Collections.nCopies;
import static sun.swing.MenuItemLayoutHelper.max;

public class Image implements Collection<ThreeComponentPixelBlock> {
    // TODO: introduce rows
    public static final ThreeComponentPixelBlock BACK_COLOR = new ThreeComponentPixelBlock(255, 0, 0);

    private int height;
    private int width;
    private List<ThreeComponentPixelBlock> pixelBlocks;

    public Image(int width, int height, ThreeComponentPixelBlock... pixelBlocks) {
        this.width = width;
        this.height = height;
        this.pixelBlocks = new ArrayList<ThreeComponentPixelBlock>(width*height);
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

    public ThreeComponentPixelBlock get(int index) {
        return pixelBlocks.get(index);
    }

    public void set(int index, ThreeComponentPixelBlock value) {
        pixelBlocks.set(index, value);
    }

    public Image horizontalJoinWith(Image other) {
        // TODO: refactoring needs
        joinFullRows(other);
        if (height < other.getHeight()) {
            joinRowsFromOtherWithBlankLines(other);
        }
        else if (height > other.getHeight()) {
            joinRowsFromThisWithBlankLines(other);
        }
        width += other.getWidth();
        height = max(height, other.getHeight());
        return this;
    }

    private void joinFullRows(Image other) {
        int minHeight = min(height, other.getHeight());
        for (int i = 0; i < minHeight; i++) {
            pixelBlocks.addAll(
                    i*(width + other.getWidth()) + width,
                    other.pixelBlocks.subList(i*other.getWidth(), (i + 1)*other.getWidth())
            );
        }
    }

    private void joinRowsFromOtherWithBlankLines(Image other) {
        List<ThreeComponentPixelBlock> blankRow = nCopies(width, BACK_COLOR);
        for (int i = height; i < other.getHeight(); i++) {
            pixelBlocks.addAll(blankRow);
            pixelBlocks.addAll(other.pixelBlocks.subList(i*other.getWidth(), (i + 1)*other.getWidth()));
        }
    }

    private void joinRowsFromThisWithBlankLines(Image other) {
        List<ThreeComponentPixelBlock> blankRow = nCopies(other.getWidth(), BACK_COLOR);
        for (int i = other.getHeight(); i < height; i++) {
                pixelBlocks.addAll(i * (width + other.getWidth()) + width, blankRow);
        }
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
        return false;
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
