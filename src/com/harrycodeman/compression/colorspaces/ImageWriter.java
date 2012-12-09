package com.harrycodeman.compression.colorspaces;

import java.io.FileOutputStream;
import java.io.OutputStream;

public abstract class ImageWriter implements IImageProcessingStage {
    private OutputStream output;

    public ImageWriter(String fileName) throws Exception {
        output = new FileOutputStream(fileName);
    }

    @Override
    public Image executeFor(Image image) throws Exception {
        saveImage(image);
        return image;
    }

    public void saveImage(Image image) throws Exception {
        saveImageImpl(image);
        output.close();
    }

    protected abstract void saveImageImpl(Image image) throws Exception;

    protected void writeIntInAscii(int value) throws Exception {
        writeString("" + value + " ");
    }

    protected void writeString(String value) throws Exception {
        char[] chars = value.toCharArray();
        for (char c : chars) {
            writeByte(c);
        }
    }

    protected void writeByte(int value) throws Exception {
        output.write(value);
    }

    protected void writeBytes(Iterable<Byte> bytes, int size) throws Exception {
        byte[] buffer = new byte[size];
        int i = 0;
        for (byte b : bytes) {
            buffer[i++] = b;
        }
        output.write(buffer);
    }
}
