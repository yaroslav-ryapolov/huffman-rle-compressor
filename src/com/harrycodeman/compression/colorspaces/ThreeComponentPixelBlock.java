package com.harrycodeman.compression.colorspaces;

import static java.lang.String.format;

public class ThreeComponentPixelBlock {
    private int first;
    private int second;
    private int third;

    public ThreeComponentPixelBlock(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int value) {
        first = value;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int value) {
        second = value;
    }

    public int getThird() {
        return third;
    }

    public void setThird(int value) {
        third = value;
    }

    public void setValues(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    // TODO: use IT!!!
    public byte[] getAsBytes() {
        return new byte[] {
                new Integer(first).byteValue(),
                new Integer(second).byteValue(),
                new Integer(third).byteValue()
        };
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == ThreeComponentPixelBlock.class
                && equals((ThreeComponentPixelBlock)other);
    }

    public boolean equals(ThreeComponentPixelBlock other) {
        return first == other.first
                && second == other.second
                && third == other.third;
    }

    @Override
    public String toString() {
        return format("[%01$d; %02$d; %03$d]", first, second, third);
    }
}
