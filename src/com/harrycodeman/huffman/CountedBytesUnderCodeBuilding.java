package com.harrycodeman.huffman;

public class CountedBytesUnderCodeBuilding extends HuffmanedByte {
    public HuffmanedByte B1;
    public HuffmanedByte B2;

    public CountedBytesUnderCodeBuilding(HuffmanedByte b1, HuffmanedByte b2) {
        super(0, b1.getReps() + b2.getReps());
        B1 = b1;
        B2 = b2;
    }
}
