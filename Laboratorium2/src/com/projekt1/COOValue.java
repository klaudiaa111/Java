package com.projekt1;

public final class COOValue {

    private final int index;
    private final Value valueOfIndex;

    public COOValue(int index, Value valueOfIndex) {
        this.index = index;
        this.valueOfIndex = valueOfIndex;
    }

    public int getIndex() {
        return index;
    }

    public Value getValueOfIndex() {
        return valueOfIndex;
    }

    @Override
    public String toString() {
        return "COOValue{" +
                "index=" + index +
                ", valueOfIndex=" + valueOfIndex +
                '}';
    }
}
