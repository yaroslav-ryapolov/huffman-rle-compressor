package com.harrycodeman.compression.colorspaces;

import java.util.Iterator;

public class YuvImageWriter extends ImageWriter {
    private class ImageAsUyvyBytes implements Iterable<Byte> {
        private class BytesIterator implements Iterator<Byte> {
            private static final int BLOCK_SIZE = 4;

            private Iterator<ThreeComponentPixelBlock> iterator = image.iterator();
            private ThreeComponentPixelBlock block;
            private int byteIndex = BLOCK_SIZE;

            @Override
            public boolean hasNext() {
                return iterator.hasNext()
                        || byteIndex < BLOCK_SIZE;
            }

            @Override
            public Byte next() {
                switch (byteIndex++) {
                    case 0:
                        return block.getSecond();
                    case 1:
                        return block.getFirst();
                    case 2:
                        return block.getThird();
                    case 3:
                        block = iterator.next();
                        return block.getFirst();
                    default:
                        block = iterator.next();
                        byteIndex = 0;
                        return next();
                }
            }

            @Override
            public void remove() {
            }
        }

        private Image image;

        public ImageAsUyvyBytes(Image image) {
            this.image = image;
        }

        @Override
        public Iterator<Byte> iterator() {
            return new BytesIterator();
        }
    }

    public YuvImageWriter(String fileName) throws Exception {
        super(fileName);
    }

    @Override
    protected void saveImageImpl(Image image) throws Exception {
        writeBytes(new ImageAsUyvyBytes(image), image.size()*2);
    }
}
