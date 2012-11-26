package com.harrycodeman.huffman;

public class HuffmanedByte implements ICountedObject {
    private int value;
    private int reps;
    private String code = "";

    public HuffmanedByte(int value) {
        this.value = value;
    }

    public HuffmanedByte(int value, int reps) {
        this(value);
        this.reps = reps;
    }

    public HuffmanedByte(int value, int reps, String code) {
        this(value, reps);
        this.code = code;
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
        if (other == null
            || other.getClass() != HuffmanedByte.class) {
            return false;
        }
        return equals((HuffmanedByte)other);
    }

    public boolean equals(HuffmanedByte other) {
        return getValue() == other.getValue()
            && getReps() == other.getReps()
            && getCode().equals(other.getCode());
    }

    @Override
    public int compareTo(ICountedObject other) {
        return getReps() - other.getReps();
    }
}
