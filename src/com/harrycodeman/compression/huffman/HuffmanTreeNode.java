package com.harrycodeman.compression.huffman;

import com.harrycodeman.compression.ByteToHexStringConverter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class HuffmanTreeNode implements ICountedObject {
    private final static String TOP_CODE = "0";
    private final static String BOTTOM_CODE = "1";

    private HuffmanedByte value;
    private HuffmanTreeNode top;
    private HuffmanTreeNode bottom;
    private int levelFromLeaf;

    public HuffmanTreeNode(HuffmanedByte value) {
        this.value = value;
    }

    public HuffmanTreeNode(HuffmanTreeNode top, HuffmanTreeNode bottom) {
        setTopAndBottom(top, bottom);
        top.appendCode(TOP_CODE);
        bottom.appendCode(BOTTOM_CODE);

    }

    public HuffmanTreeNode(HuffmanTreeNode top, HuffmanTreeNode bottom, boolean withoutAppend) {
        setTopAndBottom(top, bottom);
    }

    private void setTopAndBottom(HuffmanTreeNode top, HuffmanTreeNode bottom) {
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
        int stringLength = pow2IntoValuePlusTwo(level);
        result.add(getLeafNodeString(stringLength));
        return addBlankLines(level, stringLength, result);
    }

    private String getLeafNodeString(int stringLength) {
        String borderSpaces = generateSpaceString(stringLength/2 - 1);
        return borderSpaces + ByteToHexStringConverter.toHexString(value.getValue()) + borderSpaces;
    }

    private static List<String> addBlankLines(int level, int stringLength, List<String> result) {
        String blankLine = generateSpaceString(stringLength);
        for (int i = 0; i < level; i++) {
            result.add(blankLine);
        }
        return result;
    }

    private List<String> composeStringPresentationOfNotLeafNode(int level) {
        List<String> result = new ArrayList<String>(level + 1);
        result.add(getNotLeafNodeString(level));
        return addNotLeafChildrenStrings(level, result);
    }

    private static String getNotLeafNodeString(int level) {
        int stringLength = pow2IntoValuePlusTwo(level);
        String spaces = generateSpaceString(stringLength/4 - 1);
        String hyphens = generateHyphenString(stringLength/2);
        return spaces + TOP_CODE + hyphens + BOTTOM_CODE + spaces;
    }

    private List<String> addNotLeafChildrenStrings(int level, List<String> result) {
        List<String> topResult = top.composeStringPresentation(level - 1);
        List<String> bottomResult = bottom.composeStringPresentation(level - 1);
        for (int i = 0; i < level; i++) {
            result.add(topResult.get(i) + bottomResult.get(i));
        }
        return result;
    }

    private static int pow2IntoValuePlusTwo(int value) {
        return 4 << value;
    }

    private static String generateSpaceString(int count) {
        return generateString(count, ' ');
    }

    private static String generateHyphenString(int count) {
        return generateString(count, '-');
    }

    private static String generateString(int count, char value) {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += value;
        }
        return result;
    }
}
