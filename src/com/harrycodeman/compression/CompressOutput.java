package com.harrycodeman.compression;

public abstract class CompressOutput {
    private int currentByte;
    private int currentByteIndex;

    protected void writeSetBit() {
        int mask = 128 >> currentByteIndex;
        currentByte |= mask;
        incrementIndexAndFlushByteIfNeed();
    }

    protected void writeClearBit() {
        incrementIndexAndFlushByteIfNeed();
    }

    private void incrementIndexAndFlushByteIfNeed() {
        currentByteIndex++;
        if (currentByteIndex >= 8) {
            pushByte(currentByte);
            currentByte = 0;
            currentByteIndex = 0;
        }
    }

    protected void writeByte(int value) {
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

    protected void flushCurrentByteIfNeed() {
        if (currentByteIndex > 0) {
            pushByte(currentByte);
        }
    }

    protected abstract void pushByte(int value);
}
