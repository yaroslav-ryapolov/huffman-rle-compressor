package com.harrycodeman.compression.colorspaces;

import static java.lang.Math.abs;

public abstract class CoefficientsMatrixBase {
    // TODO: refactoring needs!!! delete this class
    private static final double EPS = 0.001;
    protected static final int WIDTH = 8;

    public abstract double get(int x, int y);
    public abstract void set(int x, int y, double value);

    public CoefficientsMatrixBase multiply(CoefficientsMatrixBase other) {
        CoefficientsMatrix result = new CoefficientsMatrix();
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                double current = 0.0;
                for (int s = 0; s < WIDTH; s++) {
                    current += get(s, y) * other.get(x, s);
                }
                result.set(x, y, current);
            }
        }
        return result;
    }

    public CoefficientsMatrixBase divideByElement(CoefficientsMatrixBase other) {
        CoefficientsMatrix result = new CoefficientsMatrix();
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                result.set(x, y, get(x, y) / other.get(x, y));
            }
        }
        return result;
    }

    public CoefficientsMatrixBase multiplyByElement(CoefficientsMatrixBase other) {
        CoefficientsMatrix result = new CoefficientsMatrix();
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                result.set(x, y, get(x, y) * other.get(x, y));
            }
        }
        return result;
    }

    public void copyByElementFrom(CoefficientsMatrixBase other) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                set(x, y, other.get(x, y));
            }
        }
    }

    public void copyByPositiveElementFrom(CoefficientsMatrixBase other) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                set(x, y, other.get(x, y) - 128);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == CoefficientsMatrix.class
                && equals((CoefficientsMatrix)other);
    }

    public boolean equals(CoefficientsMatrix other) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
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
        for (int y = 0; y < WIDTH; y++) {
            for (int x = 0; x < WIDTH; x++) {
                result += get(x, y) + ", ";
            }
            result += "\n";
        }
        return result;
    }
}
