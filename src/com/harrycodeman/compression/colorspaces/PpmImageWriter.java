package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.List;

public class PpmImageWriter extends ImageWriter {
    private List<Byte> buffer = new ArrayList<Byte>();

    public PpmImageWriter(String fileName) throws Exception {
        super(fileName);
    }

    @Override
    protected void saveImageImpl(Image image) throws Exception {
        writeString("P6 ");
        writeIntInAscii(image.getWidth());
        writeIntInAscii(image.getHeight());
        writeString("255\n");
        writePixelBlocks(image);
    }

    private void writePixelBlocks(Image image) throws Exception {
        for (ThreeComponentPixelBlock b : image) {
            buffer.add(new Integer(b.getFirst()).byteValue());
            buffer.add(new Integer(b.getSecond()).byteValue());
            buffer.add(new Integer(b.getThird()).byteValue());
        }

        byte[] bytes = new byte[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            bytes[i] = buffer.get(i);
        }
        writeBytes(bytes);
        buffer.clear();
    }
}
