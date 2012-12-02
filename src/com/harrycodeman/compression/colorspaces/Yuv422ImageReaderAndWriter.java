package com.harrycodeman.compression.colorspaces;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Yuv422ImageReaderAndWriter {
    public static SimplifiedImage loadImage(int width, int height, String filePath) throws Exception {
        InputStream input = new FileInputStream(filePath);
        SimplifiedImage result = new SimplifiedImage(width, height);
        int current = input.read();
        while (current != -1) {
            int u = current;
            int y1 = input.read();
            int v = input.read();
            int y2 = input.read();
            result.pixelBlocks.add(new Yuv444PixelBlock(y1, u, v));
            result.pixelBlocks.add(new Yuv444PixelBlock(y2, u, v));
            current = input.read();
        }
        input.close();
        return result;
    }

    public static void saveImage(SimplifiedImage image, String filePath) throws Exception {
        OutputStream output = new FileOutputStream(filePath);
        boolean isOdd = true;
        int v = 0;
        for (IPixelBlock b : image.pixelBlocks) {
            Yuv444PixelBlock yuv = (Yuv444PixelBlock)b;
            if (isOdd) {
//                output.write(yuv.getU());
//                v = yuv.getV();
                output.write(0);
            }
            else {
//                output.write(v);
                output.write(0);
            }
//            output.write(yuv.getY());
            output.write(0);
            isOdd = !isOdd;
        }
        output.close();
    }
}
