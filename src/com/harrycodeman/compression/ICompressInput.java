package com.harrycodeman.compression;

import java.io.IOException;

public interface ICompressInput extends Iterable<Integer> {
    public void close() throws IOException;
    public void reset();
}
