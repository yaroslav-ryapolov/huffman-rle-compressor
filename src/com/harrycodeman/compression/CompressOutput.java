package com.harrycodeman.compression;

public abstract class CompressOutput {
    private int currentByte;
    private int currentByteIndex;

    protected void writeSetBit() throws Exception {
        int mask = 128 >> currentByteIndex;
        currentByte |= mask;
        incrementIndexAndFlushByteIfNeed();
    }

    protected void writeClearBit() throws Exception {
        incrementIndexAndFlushByteIfNeed();
    }

    private void incrementIndexAndFlushByteIfNeed() throws Exception {
        currentByteIndex++;
        if (currentByteIndex >= 8) {
            pushByte(currentByte);
            currentByte = 0;
            currentByteIndex = 0;
        }
    }

    protected void writeByte(int value) throws Exception{
        int mask1 = (value >> currentByteIndex) & 255;
        currentByte |= mask1;

        pushByte(currentByte);
        currentByte = 0;

        if (currentByteIndex > 0) {
            int mask2 = (value << (8 - currentByteIndex));
            mask2 &= 255;
            mask2 &= (255 << (8 - currentByteIndex));
            currentByte |= mask2;
        }
    }

    protected void flushCurrentByteIfNeed() throws Exception {
        if (currentByteIndex > 0) {
            pushByte(currentByte);
        }
    }

    protected abstract void pushByte(int value) throws Exception;
}
