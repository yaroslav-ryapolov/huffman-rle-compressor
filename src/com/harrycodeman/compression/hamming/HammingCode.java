package com.harrycodeman.compression.hamming;

public class HammingCode {
    private static final int r1 = 1749;
    private static final int r2 = 1459;
    private static final int i0 = 1024;
    private static final int r3 = 911;
    private static final int i1 = 512;
    private static final int i2 = 256;
    private static final int i3 = 128;
    private static final int r4 = 127;
    private static final int i4 = 64;
    private static final int i5 = 32;
    private static final int i6 = 16;
    private static final int i7 = 8;
    private static final int i8 = 4;
    private static final int i9 = 2;
    private static final int i10 = 1;

    public static int encode(int value) {
        int result = 0;
        result |= (Integer.bitCount(value & r1) % 2) << 14;
        result |= (Integer.bitCount(value & r2) % 2) << 13;
        result |= (Integer.bitCount(value & i0) % 2) << 12;
        result |= (Integer.bitCount(value & r3) % 2) << 11;
        result |= (Integer.bitCount(value & i1) % 2) << 10;
        result |= (Integer.bitCount(value & i2) % 2) << 9;
        result |= (Integer.bitCount(value & i3) % 2) << 8;
        result |= (Integer.bitCount(value & r4) % 2) << 7;
        result |= (Integer.bitCount(value & i4) % 2) << 6;
        result |= (Integer.bitCount(value & i5) % 2) << 5;
        result |= (Integer.bitCount(value & i6) % 2) << 4;
        result |= (Integer.bitCount(value & i7) % 2) << 3;
        result |= (Integer.bitCount(value & i8) % 2) << 2;
        result |= (Integer.bitCount(value & i9) % 2) << 1;
        result |= Integer.bitCount(value & i10) % 2;
        return result;
    }
}
