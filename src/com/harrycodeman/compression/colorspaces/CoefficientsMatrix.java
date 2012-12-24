package com.harrycodeman.compression.colorspaces;

public class CoefficientsMatrix extends CoefficientsMatrixBase {
    private final double[] coefficients;

    public CoefficientsMatrix() {
        coefficients = new double[WIDTH * WIDTH];
    }

    public CoefficientsMatrix(double... coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    public double get(int x, int y) {
        return coefficients[(y * WIDTH) + x];
    }

    @Override
    public void set(int x, int y, double value) {
        coefficients[(y * WIDTH) + x] = value;
    }
}
