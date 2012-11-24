package com.harrycodeman.rle;

import java.io.IOException;

public interface ICompressByteStream {
    public boolean canRead();
    public int getNextChar();
    public void close() throws IOException;
}