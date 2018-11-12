package com.projekt1;

import java.util.Objects;

import static java.lang.Integer.parseInt;

public class Integer extends Value implements Cloneable{

    final int val;

    public Integer(){this.val = 0;}

    public Integer(int a){
        this.val=a;
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }

    @Override
    public Value add(Value v) {
        int value = 0;
        if (v instanceof Integer) {
            Integer parsedValue = (Integer) v;

            value = this.val + parsedValue.val;
        }
        return new Integer(value);

    }

    @Override
    public Value sub(Value v) {
        int value = 0;
        if (v instanceof Integer) {
            Integer parsedValue = (Integer) v;

            value = this.val - parsedValue.val;
        }
        return new Integer(value);
    }

    @Override
    public Value mul(Value v) {
        int value = 0;
        if (v instanceof Integer) {
            Integer parsedValue = (Integer) v;

            value = this.val * parsedValue.val;
        }
        return new Integer(value);
    }

    @Override
    public Value div(Value v) {
        int value = 0;
        if (v instanceof Integer) {
            Integer parsedValue = (Integer) v;

            value = this.val / parsedValue.val;
        }
        return new Integer(value);
    }

    @Override
    public Value pow(Value v) {
        int value = 0;
        if (v instanceof Integer) {
            Integer parsedValue = (Integer) v;

            value = (int) Math.pow(this.val, parsedValue.val);
        }
        return new Integer(value);
    }

    @Override
    public boolean eq(Value v) {
        if (v instanceof Integer) {
            Integer parsedValue = (Integer) v;
            if(this.val == parsedValue.val){
                return true;
            }
            else{
                return false;
            }

        }
        return false;
    }

    @Override
    public boolean neq(Value v) {
        if (v instanceof Integer) {
            Integer parsedValue = (Integer) v;
            if(this.val == parsedValue.val){
                return false;
            }
            else{
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean gte(Value v) {
        if (v instanceof Integer) {
            Integer parsedValue = (Integer) v;
            if(this.val < parsedValue.val){
                return true;
            }
            else{
                return false;
            }

        }
        return false;
    }

    @Override
    public boolean lte(Value v) {
        if (v instanceof Integer) {
            Integer parsedValue = (Integer) v;
            if(this.val > parsedValue.val){
                return true;
            }
            else{
                return false;
            }

        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        final Integer that = (Integer) other;
        if (!Objects.equals(this.val, that.val)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.val);
        return hash;
    }

    @Override
    public Value create(String s) {
        int value = parseInt(s);
        return new Integer(value);
    }

}
