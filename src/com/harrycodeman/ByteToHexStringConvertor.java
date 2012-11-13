package com.harrycodeman;

public class ByteToHexStringConvertor {
    private static final char[] hexChars =
        { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String Convert(int s) {
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
}
