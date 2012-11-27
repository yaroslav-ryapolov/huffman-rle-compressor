package com.harrycodeman.rle;

import java.util.Collection;

import static java.lang.String.format;

public class BytesBlocksToHexStringConverter {
    private static final char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
        'F' };

    public static String convert(Collection<BytesBlock> blocks) {
        BytesBlocksToHexStringConverter converter = new BytesBlocksToHexStringConverter();
        return converter.convertBlocks(blocks);
    }

    protected static String toHexString(int s) {
        // TODO: remove prefix!
        // TODO: merge with HaffmanTreeNodes methods
        final char prefix = 'x';
        return "" + prefix + getSymbol(s, 1) + getSymbol(s, 0);
    }

    private static char getSymbol(int s, int numberFromRight) {
        final int mask = 15;
        int shift = numberFromRight*4;
        int i = (s >> shift) & mask;
        return hexChars[i];
    }

    private StringBuilder result = new StringBuilder();

    protected BytesBlocksToHexStringConverter() {
    }

    private String convertBlocks(Collection<BytesBlock> blocks) {
        boolean isNotFirst = false;
        for (BytesBlock b : blocks) {
            if (isNotFirst) {
                appendBlockDelimiter();
            }
            convertBlock(b);
            isNotFirst = true;
        }
        return result.toString();
    }

    private void convertBlock(BytesBlock block) {
        if (block.getClass() == SameBytesBlock.class) {
            convertSameBytesBlock(block);
        }
        else if (block.getClass() == DifferentBytesBlock.class) {
            convertDifferentBytesBlock(block);
        }
    }

    private void convertSameBytesBlock(BytesBlock block) {
        appendBlockSizePart(block.size(), block.size() - 1);
        appendSpace();
        appendSymbolAsHexString(((SameBytesBlock)block).getSymbol());
        appendInlineDelimiter();
        appendUncompressedString(block);
    }

    private void appendBlockSizePart(int actualSize, int convertedSize) {
        result.append(format("(%1$s: %2$s)", actualSize, toHexString(convertedSize)));
    }

    private void appendSpace() {
        result.append(" ");
    }

    private void appendSymbolAsHexString(int s) {
        result.append(toHexString(s));
    }

    private void appendInlineDelimiter() {
        result.append("  |  ");
    }

    private void appendUncompressedString(BytesBlock block) {
        boolean isNotFirst = false;
        for (int s : block) {
            if (isNotFirst) {
                appendSpace();
            }
            appendSymbolAsHexString(s);
            isNotFirst = true;
        }
    }

    private void convertDifferentBytesBlock(BytesBlock block) {
        appendBlockSizePart(-block.size(), -block.size());
        appendSpace();
        appendUncompressedString(block);
        appendInlineDelimiter();
        appendUncompressedString(block);
    }

    private void appendBlockDelimiter() {
        result.append("\n");
    }
}
