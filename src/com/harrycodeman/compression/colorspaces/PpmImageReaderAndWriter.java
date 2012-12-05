package com.harrycodeman.compression.colorspaces;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class PpmImageReaderAndWriter {
    // TODO: merge with Yuv422ImageReaderAndWriter
    public static SimplifiedImage loadImage(String filePath) throws Exception {
        InputStream input = new FileInputStream(filePath);
        SimplifiedImage result = createImageFromStream(input);
        input.close();
        return result;
    }

    private static SimplifiedImage createImageFromStream(InputStream input) throws Exception {
        readFormatHeader(input);
        int width = readNextWordAsInt(input);
        int height = readNextWordAsInt(input);
        readColorLimitAndSpaces(input);
        return new SimplifiedImage(width, height, readPixelBlocks(input, width*height));
    }

    private static Collection<IPixelBlock> readPixelBlocks(InputStream input, int size) throws IOException {
        Collection<IPixelBlock> result = new ArrayList<IPixelBlock>(size);
        for (int i = 0; i < size; i++) {
            result.add(new Rgb888PixelBlock(input.read(), input.read(), input.read()));
        }
        return result;
    }

    private static void readFormatHeader(InputStream input) throws Exception {
        if (input.read() != 'P'
                || input.read() != '6') {
            input.close();
            throw new Exception("Unsupported file format!!!");
        }
    }

    private static int readNextWordAsInt(InputStream input) throws IOException {
        return Integer.parseInt(readNextWord(input));
    }

    private static String readNextWord(InputStream input) throws IOException {
        int current = readNextNonSpaceByte(input);
        String w = "";
        while (!Character.isWhitespace(current)) {
            w += (char)current;
            current = input.read();
        }
        return w;
    }

    private static int readNextNonSpaceByte(InputStream input) throws IOException {
        int current = input.read();
        while (Character.isWhitespace(current)) {
            current = input.read();
        }
        return current;
    }

    private static void readColorLimitAndSpaces(InputStream input) throws Exception {
        int current = readNextNonSpaceByte(input);
        String lim = "";
        while (!Character.isWhitespace(current)) {
            lim += (char)current;
            current = input.read();
        }
        if (Integer.parseInt(lim) != 255) {
            throw new Exception("Unsupported file format!!!");
        }
        while (current != '\n') {
            current = input.read();
        }
    }


    // TODO: refactor!!!
    public static void saveImage(SimplifiedImage image, String filePath) throws Exception {
        OutputStream output = new FileOutputStream(filePath);
        output.write('P');
        output.write('6');
        output.write(' ');
        for (char c : ("" + image.getWidth()).toCharArray()) {
            output.write(c);
        }
        output.write(' ');
        for (char c : ("" + image.getHeight()).toCharArray()) {
            output.write(c);
        }
        output.write(' ');
        output.write('2');
        output.write('5');
        output.write('5');
        output.write('\n');
        for (IPixelBlock b : image) {
            Rgb888PixelBlock rgb = (Rgb888PixelBlock)b;
            output.write(rgb.getR());
            output.write(rgb.getG());
            output.write(rgb.getB());
        }
        output.close();
    }
}
