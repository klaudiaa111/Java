package com.projekt1;

public final class COOValue {

    private final int index;
    private final Object valueOfIndex;

    public COOValue(int index, Object valueOfIndex) {
        this.index = index;
        this.valueOfIndex = valueOfIndex;
    }

    public int getIndex() {
        return index;
    }

    public Object getValueOfIndex() {
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
