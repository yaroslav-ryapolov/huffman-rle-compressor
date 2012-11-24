package com.harrycodeman.huffman;

public class CountedByte implements Comparable<CountedByte> {
    private int value;
    private int reps;

    public CountedByte(int value) {
        this.value = value;
    }

    public CountedByte(int value, int reps) {
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

    @Override
    public String toString() {
        return "" + getValue();
    }

    @Override
    public boolean equals(Object value) {
        if (value.getClass() != CountedByte.class) {
            return false;
        }
        CountedByte typedOther = (CountedByte)value;
        return getValue() == typedOther.getValue()
            && getReps() == typedOther.getReps();
    }

    @Override
    public int compareTo(CountedByte value) {
        return getReps() - value.getReps();
    }
}
