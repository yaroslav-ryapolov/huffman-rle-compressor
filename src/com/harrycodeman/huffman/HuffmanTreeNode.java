package com.harrycodeman.huffman;

public class HuffmanTreeNode {
    private HuffmanedByte value;
    private HuffmanTreeNode top;
    private HuffmanTreeNode bottom;

    public HuffmanTreeNode(HuffmanedByte value) {
        this.value = value;
    }

    public HuffmanTreeNode(HuffmanTreeNode top, HuffmanTreeNode bottom) {
        this.top = top;
        this.bottom = bottom;
    }

    public HuffmanedByte getValue() {
        return value;
    }

    public HuffmanTreeNode getTop() {
        return top;
    }

    public HuffmanTreeNode getBottom() {
        return bottom;
    }

    @Override
    public boolean equals(Object other) {
        // TODO: implement!!!
        return false;
    }
}
