package com.harrycodeman.huffman;

public class HuffmanedByte implements Comparable<HuffmanedByte> {
    private int value;
    private int reps;
    private String code;

    public HuffmanedByte(int value) {
        this.value = value;
    }

    public HuffmanedByte(int value, int reps) {
        this(value);
        this.reps = reps;
    }

    public int getValue() {
        return value;
    }

    public void incrementReps() {
        reps++;
    }

    public int getReps() {
        return reps;
    }

    public void appendCode(String value) {
        code = value + code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "" + getValue();
    }

    @Override
    public boolean equals(Object other) {
        // TODO: add comparision by code!!!
        if (other.getClass() != HuffmanedByte.class) {
            return false;
        }
        HuffmanedByte typedOther = (HuffmanedByte)other;
        return getValue() == typedOther.getValue()
            && getReps() == typedOther.getReps();
    }

    @Override
    public int compareTo(HuffmanedByte other) {
        return getReps() - other.getReps();
    }
}
