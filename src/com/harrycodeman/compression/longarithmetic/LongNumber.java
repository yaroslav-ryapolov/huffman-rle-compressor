package com.harrycodeman.compression.longarithmetic;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;
import static java.util.Collections.nCopies;

public class LongNumber {
    private static final long LONG_BASE = 1073741824L;
    private static final long SOURCE_BASE = 1000000000L;

    private List<Integer> digits = new ArrayList<Integer>();

    public LongNumber(Integer... digits) {
        addAll(this.digits, digits);
    }

    public LongNumber add(LongNumber other) throws Exception {
        LongNumber result = new LongNumber();
        int length = alignDigits(other);
        int k = 0;
        for (int j = 0; j < length; j++) {
            long value = (long)digits.get(j) + (long)other.digits.get(j) + (long)k;
            result.digits.add((int)(value % LONG_BASE));
            k = (int)(value / LONG_BASE);
        }
        if (k != 0) {
            result.digits.add(k);
        }
        return result;
    }

    private int alignDigits(LongNumber other) {
        while (digits.size() < other.digits.size()) {
            digits.add(0);
        }
        while (digits.size() > other.digits.size()) {
            other.digits.add(0);
        }
        return digits.size();
    }

    public LongNumber subtract(LongNumber other) throws Exception {
        LongNumber result = new LongNumber();
        int length = alignDigits(other);
        int k = 0;
        for (int j = 0; j < length; j++) {
            long value = (long)digits.get(j) - (long)other.digits.get(j) + (long)k;
            result.digits.add((int)(value % LONG_BASE));
            k = value < 0 ? -1 : 0;
        }
        return result;
    }

    public LongNumber multiply(LongNumber other) throws Exception {
        LongNumber result = new LongNumber();
        result.digits = new ArrayList<Integer>(nCopies(digits.size() + other.digits.size() - 1, 0));
        for (int j = 0; j < digits.size(); j++) {
            if (digits.get(j) == 0) {
                result.digits.add(0);
            }
            else {
                int k = 0;
                for (int i = 0; i < other.digits.size(); i++) {
                    long t = (long)digits.get(j) * (long)other.digits.get(i) + (long)result.digits.get(j + i) + (long)k;
                    result.digits.set(j + i, (int)(t % LONG_BASE));
                    k = (int)(t / LONG_BASE);
                }
                if (k != 0) {
                    result.digits.set(j + other.digits.size(), k);
                }
            }
        }
        return result;
    }

    public LongNumber divide(LongNumber other) throws Exception {
        return this;
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == LongNumber.class
                && equals((LongNumber)other);
    }

    public boolean equals(LongNumber other) {
        return digits.equals(other.digits);
    }

    @Override
    public String toString() {
        String result = "";
        for (int d : digits) {
            result = d + " " + result;
        }
        return result;
    }
}
