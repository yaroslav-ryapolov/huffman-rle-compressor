package com.harrycodeman.huffman;

public class CodedByte {
    private int symbol;
    private String code;

    public CodedByte(int symbol, String code) {
        this.symbol = symbol;
        this.code = code;
    }

    public int getSymbol() {
        return symbol;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object value) {
        if (value.getClass() != CodedByte.class) {
            return false;
        }
        CodedByte typedValue = (CodedByte)value;
        return getSymbol() == typedValue.getSymbol()
            && getCode().equals(typedValue.getCode());
    }

    @Override
    public String toString() {
        return "(" + symbol + "; " + code + ")";
    }
}
