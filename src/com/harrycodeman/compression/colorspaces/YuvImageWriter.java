package com.harrycodeman.compression.colorspaces;

import java.util.ArrayList;
import java.util.List;

public class YuvImageWriter extends ImageWriter {
    private List<Byte> buffer = new ArrayList<Byte>();
    private Integer y1;
    private Integer y2;
    private Integer u;
    private Integer v;

    public YuvImageWriter(String fileName) throws Exception {
        super(fileName);
    }

    @Override
    protected void saveImageImpl(Image image) throws Exception {
        boolean isOdd = true;
        for (ThreeComponentPixelBlock b : image) {
            if (isOdd) {
                y1 = b.getFirst();
                u = b.getSecond() + 128;
                v = b.getThird() + 128;
            }
            else {
                y2 = b.getFirst();
                writeOctet();
            }
            isOdd = !isOdd;
        }

        byte[] bytes = new byte[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            bytes[i] = buffer.get(i);
        }
        writeBytes(bytes);
        buffer.clear();
    }

    private void writeOctet() throws Exception {
        buffer.add(u.byteValue());
        buffer.add(y1.byteValue());
        buffer.add(v.byteValue());
        buffer.add(y2.byteValue());
    }
}
