package com.harrycodeman.rle;

public class SameBytesBlock extends BytesBlock {
    private int symbol;
    private int count;

    public SameBytesBlock(int symbol, int count) throws Exception {
        this.symbol = symbol;
        this.count = count;
        if (isBlockOverflowed()) {
            throw new Exception("Attempt to create overflowed block!");
        }
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

    @Override
    public boolean equals(Object value) {
        if (value.getClass() != SameBytesBlock.class) {
            return false;
        }
        SameBytesBlock typedValue = (SameBytesBlock)value;
        return size() == typedValue.size()
            && symbol == typedValue.symbol;
    }
}
