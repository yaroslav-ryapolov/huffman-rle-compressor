package com.harrycodeman.compression;

import java.io.IOException;

// TODO: solve Exceptions problem for whole solution!!!
public interface ICompressInput extends Iterable<Integer> {
    public void close() throws IOException;
    public void reset();
}
