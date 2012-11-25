package com.harrycodeman;

import java.io.IOException;

public interface ICompressByteStream extends Iterable<Integer> {
    public void close() throws IOException;
}
