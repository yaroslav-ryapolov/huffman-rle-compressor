package com.harrycodeman.compression.longarithmetic;

public class LongNumberDivideResult {
    private final LongNumber quotient;
    private final LongNumber rest;

    public LongNumberDivideResult(LongNumber quotient, LongNumber rest) {
        this.quotient = quotient;
        this.rest = rest;
    }

    public LongNumber getQuotient() {
        return quotient;
    }

    public LongNumber getRest() {
        return rest;
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == LongNumberDivideResult.class
                && equals((LongNumberDivideResult)other);
    }

    public boolean equals(LongNumberDivideResult other) {
        return quotient.equals(other.quotient)
                && rest.equals(other.rest);
    }

    @Override
    public String toString() {
        return "Quotient: " + quotient + "; Rest: " + rest + ";";
    }
}
