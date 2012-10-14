package com.harrycodeman;

import java.util.ArrayList;
import java.util.List;

public class ByteRleCompressor implements ICompressStream {
    private List<Byte> bytes = new ArrayList<Byte>();

    @Override
    public byte getNextByte() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
