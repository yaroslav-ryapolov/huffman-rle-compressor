package com.harrycodeman.compression.hamming;

public class HammingCode {
    private static final int r0 = 1749;
    private static final int r1 = 1459;
    private static final int r2 = 911;
    private static final int r3 = 127;

    public static int encode7by4(int value) {
        int result = value << 4;
        result |= multiplyByMode2(value, r0) << 3;
        result |= multiplyByMode2(value, r1) << 2;
        result |= multiplyByMode2(value, r2) << 1;
        result |= multiplyByMode2(value, r3);
        return result;
    }

    private static int multiplyByMode2(int row, int column) {
        return Integer.bitCount(row & column) & 1;
    }

    

//    public static long encode63by57(int value) {
//        long result = value << 6;
//
//        return result;
//    }
//
//    private static long multiplyByMode2(long row, long column) {
//        return Long.bitCount(row & column) & 1;
//    }
}
