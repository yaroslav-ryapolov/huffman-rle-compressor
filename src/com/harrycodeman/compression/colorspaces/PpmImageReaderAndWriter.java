package com.harrycodeman.compression.colorspaces;

import java.io.*;

public class PpmImageReaderAndWriter {
    public static SimplifiedImage loadImage(String filePath) throws Exception {
        InputStream input = new FileInputStream(filePath);
        if (input.read() != 'P') {
            return null;
        }
        if (input.read() != '6') {
            return null;
        }
        input.read();

        int current = input.read();
        String width = "";
        while (current != ' ') {
            width += (char)current;
            current = input.read();
        }

        current = input.read();
        String height = "";
        while (current != ' ') {
            height += (char)current;
            current = input.read();
        }

        current = input.read();
        String limit = "";
        while (current != '\n') {
            limit += (char)current;
            current = input.read();
        }

        SimplifiedImage result = new SimplifiedImage(Integer.parseInt(width), Integer.parseInt(height));
        int fullImageSize = Integer.parseInt(width)*Integer.parseInt(height);
        for (int i = 0; i < fullImageSize; i++) {
            result.pixelBlocks.add(new Rgb888PixelBlock(input.read(), input.read(), input.read()));
        }
        input.close();
        return result;
    }

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
        for (IPixelBlock b : image.pixelBlocks) {
            Rgb888PixelBlock rgb = (Rgb888PixelBlock)b;
            output.write(rgb.getR());
            output.write(rgb.getG());
            output.write(rgb.getB());
        }
        output.close();
    }
}
