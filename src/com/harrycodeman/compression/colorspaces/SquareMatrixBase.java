package com.harrycodeman.compression.colorspaces;

import static java.lang.Math.abs;

public abstract class SquareMatrixBase {
    // TODO: refactoring needs!!! delete this class
    private static final double EPS = 0.001;
    protected final int dim;

    public abstract double get(int x, int y);
    public abstract void set(int x, int y, double value);

    public SquareMatrixBase(int dim) {
        this.dim = dim;
    }

    public int getDim() {
        return dim;
    }

    public SquareMatrixBase multiply(SquareMatrixBase other) {
        SquareMatrix result = new SquareMatrix(dim);
        for (int x = 0; x < dim; x++) {
            for (int y = 0; y < dim; y++) {
                double current = 0.0;
                for (int s = 0; s < dim; s++) {
                    current += get(s, y) * other.get(x, s);
                }
                result.set(x, y, current);
            }
        }
        return result;
    }

    public SquareMatrixBase divideByElement(SquareMatrixBase other) {
        SquareMatrix result = new SquareMatrix(dim);
        for (int x = 0; x < dim; x++) {
            for (int y = 0; y < dim; y++) {
                result.set(x, y, get(x, y) / other.get(x, y));
            }
        }
        return result;
    }

    public SquareMatrixBase multiplyByElement(SquareMatrixBase other) {
        SquareMatrix result = new SquareMatrix(dim);
        for (int x = 0; x < dim; x++) {
            for (int y = 0; y < dim; y++) {
                result.set(x, y, get(x, y) * other.get(x, y));
            }
        }
        return result;
    }

    public void copyByElementFrom(SquareMatrixBase other) {
        for (int x = 0; x < dim; x++) {
            for (int y = 0; y < dim; y++) {
                set(x, y, other.get(x, y));
            }
        }
    }

    public void copyByPositiveElementFrom(SquareMatrixBase other) {
        for (int x = 0; x < dim; x++) {
            for (int y = 0; y < dim; y++) {
                set(x, y, other.get(x, y) - 128);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == SquareMatrix.class
                && equals((SquareMatrix)other);
    }

    public boolean equals(SquareMatrix other) {
        for (int x = 0; x < dim; x++) {
            for (int y = 0; y < dim; y++) {
                if (abs(get(x, y) - other.get(x, y)) > EPS) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "";
        for (int y = 0; y < dim; y++) {
            for (int x = 0; x < dim; x++) {
                result += get(x, y) + ", ";
            }
            result += "\n";
        }
        return result;
    }
}
