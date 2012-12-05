package com.harrycodeman.compression.colorspaces;

public class PpmImageWriter extends ImageWriter {
    public PpmImageWriter(String fileName) throws Exception {
        super(fileName);
    }

    @Override
    protected void saveImageImpl(SimplifiedImage image) throws Exception {
        writeString("P6 ");
        writeIntInAscii(image.getWidth());
        writeIntInAscii(image.getHeight());
        writeString("255\n");
        writePixelBlocks(image);
    }

    private void writePixelBlocks(SimplifiedImage image) throws Exception {
        for (IPixelBlock b : image) {
            Rgb888PixelBlock rgb = (Rgb888PixelBlock)b;
            writeBytes(rgb.getR(), rgb.getG(), rgb.getB());
        }
    }
}
