package com.harrycodeman.compression;

public class ByteToHexStringConverter {
    private static final char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F' };

    public static String toHexString(int s) {
        return "" + getSymbol(s, 1) + getSymbol(s, 0);
    }

    private static char getSymbol(int s, int numberFromRight) {
        final int mask = 15;
        int shift = numberFromRight*4;
        int i = (s >> shift) & mask;
        return hexChars[i];
    }
}
