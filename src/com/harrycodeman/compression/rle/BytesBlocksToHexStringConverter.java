package com.harrycodeman.compression.rle;

import java.util.Collection;

import static java.lang.String.format;

public class BytesBlocksToHexStringConverter {
    public static String convert(Collection<BytesBlock> blocks) {
        BytesBlocksToHexStringConverter converter = new BytesBlocksToHexStringConverter();
        return converter.convertBlocks(blocks);
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
        appendBlockSizePart(block.size(), (byte)(block.size() - 1));
        appendSpace();
        appendSymbolAsHexString(((SameBytesBlock)block).getSymbol());
        appendInlineDelimiter();
        appendUncompressedString(block);
    }

    private void appendBlockSizePart(int actualSize, byte convertedSize) {
        result.append(format("(%1$s: %2$02X)", actualSize, convertedSize));
    }

    private void appendSpace() {
        result.append(" ");
    }

    private void appendSymbolAsHexString(int s) {
        result.append(format("%1$02X", s));
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
        appendBlockSizePart(-block.size(), (byte)(-block.size()));
        appendSpace();
        appendUncompressedString(block);
        appendInlineDelimiter();
        appendUncompressedString(block);
    }

    private void appendBlockDelimiter() {
        result.append("\n");
    }
}
