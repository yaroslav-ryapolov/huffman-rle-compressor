package com.harrycodeman.compression.hamming;

public class HammingCode {
    private static final int encode0 = 1749;        // 11011010101
    private static final int encode1 = 1459;        // 10110110011
    private static final int encode2 = 911;         // 01110001111
    private static final int encode3 = 127;         // 00001111111
    private static final int encodeParity = 65534;  // 11111111111 11110

    private static final int decode0 = 55984;       // 11011010101 10000
    private static final int decode1 = 46696;       // 10110110011 01000
    private static final int decode2 = 29156;       // 01110001111 00100
    private static final int decode3 = 4066;        // 00001111111 00010
    private static final int decodeParity = 65535;  // 11111111111 11111

    private static final int[] hMatrix = {
            1,  // 00001
            3,  // 00011
            5,  // 00101
            9,  // 01001
            17, // 10001
            31, // 11111
            15, // 01111
            23, // 10111
            7,  // 00111
            27, // 11011
            11, // 01011
            19, // 10011
            29, // 11101
            13, // 01101
            21, // 10101
            25, // 11001
    };

    public static int encode16by11(int value) {
        int result = value << 5;
        result |= multiplyByMode2(value, encode0) << 4;
        result |= multiplyByMode2(value, encode1) << 3;
        result |= multiplyByMode2(value, encode2) << 2;
        result |= multiplyByMode2(value, encode3) << 1;
        result |= multiplyByMode2(result, encodeParity);
        return result;
    }

    private static int multiplyByMode2(int row, int column) {
        return Integer.bitCount(row & column) & 1;
    }

    public static int decode16by11(int value) throws Exception {
        int s = calculateSyndrome(value);
        if (s != 0) {
            int e = getErrorBit(s);
            if (e < hMatrix.length) {
                value = invertBit(value, e);
            }
            else {
                throw new UnrecoverableErrorsException();
            }
        }
        return value >>> 5;
    }

    private static int calculateSyndrome(int value) {
        int s = multiplyByMode2(value, decode0) << 4;
        s |= multiplyByMode2(value, decode1) << 3;
        s |= multiplyByMode2(value, decode2) << 2;
        s |= multiplyByMode2(value, decode3) << 1;
        s |= multiplyByMode2(value, decodeParity);
        return s;
    }

    private static int getErrorBit(int s) {
        int i = 0;
        for (int hRow : hMatrix) {
            if (s == hRow) {
                return i;
            }
            i++;
        }
        return i;
    }

    private static int invertBit(int value, int index) {
        int mask = 1 << index;
        int bitError = value & mask;
        if (bitError > 0) {
            return value & ~mask;
        }
        return value | mask;
    }
}
