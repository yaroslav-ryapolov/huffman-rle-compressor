package com.harrycodeman.huffman;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class HuffmanTreeNode implements ICountedObject {
    private static final char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F' };

    private HuffmanedByte value;
    private HuffmanTreeNode top;
    private HuffmanTreeNode bottom;
    private int levelFromLeaf;

    public HuffmanTreeNode(HuffmanedByte value) {
        this.value = value;
    }

    public HuffmanTreeNode(HuffmanTreeNode top, HuffmanTreeNode bottom) {
        top.appendCode("0");
        this.top = top;
        bottom.appendCode("1");
        this.bottom = bottom;
        levelFromLeaf = max(top.getLevelFromLeaf(), bottom.getLevelFromLeaf()) + 1;
    }

    public HuffmanTreeNode(HuffmanTreeNode top, HuffmanTreeNode bottom, boolean withoutAppend) {
        this.top = top;
        this.bottom = bottom;
        levelFromLeaf = max(top.getLevelFromLeaf(), bottom.getLevelFromLeaf()) + 1;
    }

    public HuffmanedByte getValue() {
        return value;
    }

    public void appendCode(String codePart) {
        if (hasValue()) {
            value.appendCode(codePart);
        }
        else {
            top.appendCode(codePart);
            bottom.appendCode(codePart);
        }
    }

    public HuffmanTreeNode getTop() {
        return top;
    }

    public HuffmanTreeNode getBottom() {
        return bottom;
    }

    public boolean hasValue() {
        return value != null;
    }

    private int getLevelFromLeaf() {
        return levelFromLeaf;
    }

    @Override
    public int getReps() {
        if (hasValue()) {
            return value.getReps();
        }
        return top.getReps() + bottom.getReps();
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == HuffmanTreeNode.class
                && equals((HuffmanTreeNode) other);
    }

    public boolean equals(HuffmanTreeNode other) {
        if (hasValue()
            && other.hasValue()) {
            return getValue().equals(other.getValue());
        }
        return !hasValue()
                && !other.hasValue()
                && getTop().equals(other.getTop())
                && getBottom().equals(other.getBottom());
    }

    @Override
    public int compareTo(ICountedObject other) {
        return getReps() - other.getReps();
    }

    @Override
    public String toString() {
        String result = "";
        for (String line : composeStringPresentation(levelFromLeaf)) {
            result += line + "\n";
        }
        return result;
    }

    private List<String> composeStringPresentation(int level) {
        if (hasValue()) {
            return composeStringPresentationOfValue(level);
        }
        return composeStringPresentationOfNotLeafNode(level);
    }

    private List<String> composeStringPresentationOfValue(int level) {
        List<String> result = new ArrayList<String>(level + 1);
        int spaceCount = (2 << level) - 1;
        int blankLineSpaceCount = 4 << level;
        result.add(generateSpaceString(spaceCount) + toHexString(value.getValue()) + generateSpaceString(spaceCount));
        for (int i = 0; i < level; i++) {
            result.add(generateSpaceString(blankLineSpaceCount));
        }
        return result;
    }

    private static String toHexString(int s) {
        return "" + getSymbol(s, 1) + getSymbol(s, 0);
    }

    private static char getSymbol(int s, int numberFromRight) {
        final int mask = 15;
        int shift = numberFromRight*4;
        int i = (s >> shift) & mask;
        return hexChars[i];
    }

    private List<String> composeStringPresentationOfNotLeafNode(int level) {
        List<String> result = new ArrayList<String>(level + 1);
        int spaceCount = (1 << level) - 1;
        int hyphenCount = 2 << level;
        result.add(generateSpaceString(spaceCount) +
                "0" + generateHyphenString(hyphenCount) + "1" +
                generateSpaceString(spaceCount));

        List<String> topResult = top.composeStringPresentation(level - 1);
        List<String> bottomResult = bottom.composeStringPresentation(level - 1);
        for (int i = 0; i < level; i++) {
            result.add(topResult.get(i) + bottomResult.get(i));
        }
        return result;
    }

    private static String generateSpaceString(int count) {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += " ";
        }
        return result;
    }

    private static String generateHyphenString(int count) {
        String result ="";
        for (int i = 0; i < count; i++) {
            result += "-";
        }
        return result;
    }
}
