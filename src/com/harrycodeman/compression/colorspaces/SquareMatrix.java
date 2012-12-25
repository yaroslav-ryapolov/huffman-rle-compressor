package com.harrycodeman.compression.colorspaces;

public class SquareMatrix extends SquareMatrixBase {
    private final double[] coefficients;

    public SquareMatrix(int dim) {
        super(dim);
        coefficients = new double[dim * dim];
    }

    public SquareMatrix(int dim, double... coefficients) {
        super(dim);
        this.coefficients = coefficients;
    }

    @Override
    public double get(int x, int y) {
        return coefficients[(y * dim) + x];
    }

    @Override
    public void set(int x, int y, double value) {
        coefficients[(y * dim) + x] = value;
    }
}
