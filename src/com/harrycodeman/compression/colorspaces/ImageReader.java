package com.harrycodeman.compression.colorspaces;

import java.io.FileInputStream;
import java.io.InputStream;

public abstract class ImageReader implements IImageProcessingStage {
    private static final int BUFFER_LENGTH = 1024;

    private byte[] buffer = new byte[BUFFER_LENGTH];
    private int indexInBuffer = 0;
    private int currentBufferLength = 0;
    private boolean isEof;
    private InputStream input;
    protected int lastByte;

    public ImageReader(String fileName) throws Exception {
        input = new FileInputStream(fileName);
    }

    public Image loadImage() throws Exception {
        Image result = loadImageToImpl();
        input.close();
        return result;
    }

    protected abstract Image loadImageToImpl() throws Exception;

    protected int readNextWordAsInt() throws Exception {
        return Integer.parseInt(readNextWord());
    }

    protected String readNextWord() throws Exception {
        readNextNonSpaceByte();
        String w = "";
        while (!Character.isWhitespace(lastByte)) {
            w += (char)lastByte;
            readByteAsInt();
        }
        return w;
    }

    protected int readByteAsInt() throws Exception {
        return lastByte = readByte();
    }

    private byte readByte() throws Exception {
        if (indexInBuffer == currentBufferLength) {
            currentBufferLength = input.read(buffer);
            indexInBuffer = 0;
        }
        if (indexInBuffer > currentBufferLength) {
            isEof = true;
            return -1;
        }
        return buffer[indexInBuffer++];
    }

    private void readNextNonSpaceByte() throws Exception {
        readByteAsInt();
        while (Character.isWhitespace(lastByte)) {
            readByteAsInt();
        }
    }

    protected boolean isNotEof() {
        return !isEof;
    }

    @Override
    public Image executeFor(Image image) throws Exception {
        return loadImage();
    }
}
