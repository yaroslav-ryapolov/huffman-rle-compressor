package com.harrycodeman.compression.colorspaces;

import java.util.Iterator;

public class PpmImageWriter extends ImageWriter {
    private class ImageAsRgbBytes implements Iterable<Byte> {
        private class BytesIterator implements Iterator<Byte> {
            private static final int BLOCK_SIZE = 3;

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
                        return block.getFirst();
                    case 1:
                        return block.getSecond();
                    case 2:
                        return block.getThird();
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

        public ImageAsRgbBytes(Image image) {
            this.image = image;
        }

        @Override
        public Iterator<Byte> iterator() {
            return new BytesIterator();
        }
    }

    public PpmImageWriter(String fileName) throws Exception {
        super(fileName);
    }

    @Override
    protected void saveImageImpl(Image image) throws Exception {
        writeString("P6 ");
        writeIntInAscii(image.getWidth());
        writeIntInAscii(image.getHeight());
        writeString("255\n");
        writeBytes(new ImageAsRgbBytes(image), image.size()*3);
    }
}
