package com.harrycodeman;

public class ToHexStringConvertor {
    private static final char[] hexChars =
        { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String ConvertByte(int s) {
        final char prefix = 'x';
        return "" + prefix + getByteSymbols(s);
    }

    private static String getByteSymbols(int s) {
        int s1 = getFirstSymbolIndex(s);
        int s2 = getSecondSymbolIndex(s);
        return "" + hexChars[s1] + hexChars[s2];
    }

    private static int getFirstSymbolIndex(int s) {
        final int mask = 240;
        final int shift = 4;
        return (s & mask) >> shift;
    }

    private static int getSecondSymbolIndex(int s) {
        final int mask = 15;
        return s & mask;
    }

    public static String ConvertCounter(int s) {
        // TODO: BitSet!!!
        int s1 = getFirst111SymbolIndex(s);
        int s2 = getSecond111SymbolIndex(s);
        int s3 = getFirst11SymbolIndex(s);
        int s4 = getSecond11SymbolIndex(s);
        int s5 = getFirst1SymbolIndex(s);
        int s6 = getSecond1SymbolIndex(s);
        int s7 = getFirstSymbolIndex(s);
        int s8 = getSecondSymbolIndex(s);
        return "x" + hexChars[s1] + hexChars[s2] +
            " x" + hexChars[s3] + hexChars[s4] +
            " x" + hexChars[s5] + hexChars[s6] +
            " x" + hexChars[s7] + hexChars[s8];
    }

    private static int getFirst1SymbolIndex(int s) {
        final int mask = 61440;
        final int shift = 12;
        return (s & mask) >> shift;
    }

    private static int getSecond1SymbolIndex(int s) {
        final int mask = 3840;
        final int shift = 8;
        return (s & mask) >> shift;
    }

    private static int getFirst11SymbolIndex(int s) {
        final int mask = 15728640;
        final int shift = 20;
        return (s & mask) >> shift;
    }

    private static int getSecond11SymbolIndex(int s) {
        final int mask = 983040;
        final int shift = 16;
        return (s & mask) >> shift;
    }

    private static int getFirst111SymbolIndex(int s) {
        final int mask = -268435456;
        final int shift = 28;
        return (s & mask) >> shift;
    }

    private static int getSecond111SymbolIndex(int s) {
        final int mask = 251658240;
        final int shift = 24;
        return (s & mask) >> shift;
    }

    private static int getSymbolIndex(int s) {
        final int mask = 15;
        return s & mask;
    }

    private static int getLastSymbolIndex(int s) {
        final int mask = 15;
        return s & mask;
    }
}
