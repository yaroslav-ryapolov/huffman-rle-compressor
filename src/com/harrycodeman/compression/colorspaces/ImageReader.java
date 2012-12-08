package com.harrycodeman.compression.colorspaces;

import java.io.FileInputStream;
import java.io.InputStream;

public abstract class ImageReader {
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
            readByteIntoLastByte();
        }
        return w;
    }

    protected int readByteIntoLastByte() throws Exception {
        return lastByte = input.read();
    }

    private void readNextNonSpaceByte() throws Exception {
        readByteIntoLastByte();
        while (Character.isWhitespace(lastByte)) {
            readByteIntoLastByte();
        }
    }

    protected boolean isNotEof() {
        return lastByte != -1;
    }
}
