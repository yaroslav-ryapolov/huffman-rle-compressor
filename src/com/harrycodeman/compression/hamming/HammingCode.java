package com.harrycodeman.compression.hamming;

public class HammingCode {
    private static final int r1 = 0b11011010101;
    private static final int r2 = 0b10110110011;
    private static final int i0 = 0b10000000000;
    private static final int r3 = 0b01110001111;
    private static final int i1 = 0b01000000000;
    private static final int i2 = 0b00100000000;
    private static final int i3 = 0b00010000000;
    private static final int r4 = 0b00001111111;
    private static final int i4 = 0b00001000000;
    private static final int i5 = 0b00000100000;
    private static final int i6 = 0b00000010000;
    private static final int i7 = 0b00000001000;
    private static final int i8 = 0b00000000100;
    private static final int i9 = 0b00000000010;
    private static final int i10 = 0b00000000001;

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
