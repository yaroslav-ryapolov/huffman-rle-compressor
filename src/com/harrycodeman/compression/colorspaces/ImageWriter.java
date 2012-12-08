package com.harrycodeman.compression.colorspaces;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class ImageWriter implements IImageProcessingStage {
    private OutputStream output;

    public ImageWriter(String fileName) throws Exception {
        output = new FileOutputStream(fileName);
    }

    @Override
    public void executeFor(Image image) throws Exception {
        saveImage(image);
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

    protected void writeBytes(int... value) throws IOException {
        for (int b : value) {
            output.write(b);
        }
    }

    protected void writeBytes(byte[] bytes) throws Exception {
        output.write(bytes);
    }
}
