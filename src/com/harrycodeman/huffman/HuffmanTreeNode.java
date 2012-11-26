package com.harrycodeman.huffman;

public class HuffmanTreeNode implements ICountedObject {
    private HuffmanedByte value;
    private HuffmanTreeNode top;
    private HuffmanTreeNode bottom;

    public HuffmanTreeNode(HuffmanedByte value) {
        this.value = value;
    }

    public HuffmanTreeNode(HuffmanTreeNode top, HuffmanTreeNode bottom) {
        top.appendCode("0");
        this.top = top;
        bottom.appendCode("1");
        this.bottom = bottom;
    }

    public HuffmanTreeNode(HuffmanTreeNode top, HuffmanTreeNode bottom, boolean withoutAppend) {
        this.top = top;
        this.bottom = bottom;
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

    @Override
    public int getReps() {
        if (hasValue()) {
            return value.getReps();
        }
        return top.getReps() + bottom.getReps();
    }

    @Override
    public boolean equals(Object other) {
        if (other.getClass() != HuffmanTreeNode.class) {
            return false;
        }
        return equals((HuffmanTreeNode)other);
    }

    public boolean equals(HuffmanTreeNode other) {
        if (hasValue()
            && other.hasValue()) {
            return getValue().equals(other.getValue());
        }
        if (!hasValue()
            && !other.hasValue()) {
            return getTop().equals(other.getTop())
                && getBottom().equals(other.getBottom());
        }
        return false;
    }

    @Override
    public int compareTo(ICountedObject other) {
        return getReps() - other.getReps();
    }
}
