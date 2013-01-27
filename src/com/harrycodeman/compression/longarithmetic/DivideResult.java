package com.harrycodeman.compression.longarithmetic;

public class DivideResult {
    private final LongNumber quotient;
    private final LongNumber rest;

    public DivideResult(LongNumber quotient, LongNumber rest) {
        this.quotient = quotient;
        this.rest = rest;
    }

    public LongNumber getQuotient() {
        return quotient;
    }

    public LongNumber getRest() {
        return rest;
    }
}
