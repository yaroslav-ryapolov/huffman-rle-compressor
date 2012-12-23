package com.harrycodeman.compression.colorspaces;

public class CoefficientsMatrix {
    private final int width;
    private final double[] coefficients;

    public CoefficientsMatrix(int width, double... coefficients) {
        this.width = width;
        this.coefficients = coefficients;
    }

    public double get(int x, int y) {
        return coefficients[(y * width) + x];
    }
}
