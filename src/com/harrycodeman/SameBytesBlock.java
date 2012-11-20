package com.harrycodeman;

import java.io.IOException;

public class SameBytesBlock extends BytesBlock implements ICompressByteStream {
    private int symbol;
    private int count;

    public SameBytesBlock(int symbol, int count) {
        this.symbol = symbol;
        this.count = count;
    }

    @Override
    public boolean isSymbolSuitableForBlock(int s) {
        return symbol == s;
    }

    @Override
    protected void addSymbolOverridden(int s) {
        count++;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toHexString() {
        String hex = ToHexStringConverter.convertByte(symbol);
        return "(" + count + ") " + hex + " ||| " + GetUncompressedString(hex) + "\n";
    }

    private String GetUncompressedString(String hex) {
        String uncompressed = "";
        for (int i = 0; i < count; i++) {
            uncompressed += hex + " ";
        }
        return uncompressed;
    }

    private int streamIndex = 0;

    @Override
    public boolean canRead() {
        return streamIndex < count;
    }

    @Override
    public int getNextChar() {
        streamIndex++;
        return symbol;
    }

    @Override
    public void close() throws IOException {
        streamIndex = 0;
    }
}
