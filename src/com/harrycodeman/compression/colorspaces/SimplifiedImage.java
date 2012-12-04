package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SimplifiedImage implements Iterable<IPixelBlock> {
    private int height;
    private int width;
    // TODO: do private
    public List<IPixelBlock> pixelBlocks = new ArrayList<IPixelBlock>();

    public SimplifiedImage(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private SimplifiedImage(int width, int height, Collection<IPixelBlock> pixelBlocks) {
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

    public SimplifiedImage convertByPixelBlocks(IPixelBlockConverter converter) throws Exception {
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
    public Iterator<IPixelBlock> iterator() {
        return pixelBlocks.iterator();
    }
}
