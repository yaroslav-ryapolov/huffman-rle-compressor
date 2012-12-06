package com.harrycodeman.compression.colorspaces;

public class Yuv422ImageWriter extends ImageWriter {
    private int y1;
    private int y2;
    private int u;
    private int v;

    public Yuv422ImageWriter(String fileName) throws Exception {
        super(fileName);
    }

    @Override
    protected void saveImageImpl(SimplifiedImage image) throws Exception {
        boolean isOdd = true;
        for (IPixelBlock b : image) {
            Yuv444PixelBlock yuv = (Yuv444PixelBlock)b;
            if (isOdd) {
                y1 = yuv.getY();
                u = yuv.getU() + 128;
                v = yuv.getV() + 128;
            }
            else {
                y2 = yuv.getY();
                writeOctet();
            }
            isOdd = !isOdd;
        }
    }

    private void writeOctet() throws Exception {
        writeBytes(u, y1, v, y2);
    }
}
