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
        int length = alignDigitsByLeftZeros(other);
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

    private int alignDigitsByLeftZeros(LongNumber other) {
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
        int length = alignDigitsByLeftZeros(other);
        int k = 0;
        for (int j = 0; j < length; j++) {
            long value = (long)digits.get(j) - (long)other.digits.get(j) + (long)k;
            result.digits.add((int)((value + LONG_BASE) % LONG_BASE));
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

    public LongNumberDivideResult divide(LongNumber other) throws Exception {
        LongNumber dividend = new LongNumber();
        dividend.digits = new ArrayList<Integer>(digits);
        LongNumber quotient = new LongNumber();
        int lengthDiff = other.digits.size() - digits.size();
        while (other.digits.size() < digits.size()) {
            other.digits.add(0, 0);
        }
        for (int i = 0; i <= lengthDiff; i++) {
            int w;
            for (w = 0; dividend.isGreaterThanOrEqual(other); w++) {
                dividend = dividend.subtract(other);
            }
            quotient.digits.add(0, w);
            other.digits.remove(0);
        }

        return new LongNumberDivideResult(quotient, dividend);
    }

    public boolean isGreaterThanOrEqual(LongNumber other) throws Exception {
        if (digits.size() != other.digits.size()) {
            return digits.size() > other.digits.size();
        }
        for (int i = digits.size() - 1; i >= 0; i--) {
            if (!digits.get(i).equals(other.digits.get(i))) {
                return digits.get(i) > other.digits.get(i);
            }
        }
        return true;
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
