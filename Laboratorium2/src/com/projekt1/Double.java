package com.projekt1;

import java.util.Objects;

import static java.lang.Double.parseDouble;

public class Double extends Value implements Cloneable{

    final double val;

    public Double(){this.val = 0;}

    public Double(double a){
        this.val=a;
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }

    @Override
    public Value add(Value v) {
        double value = 0;
        if (v instanceof Double) {
            Double parsedValue = (Double) v;

            value = this.val + parsedValue.val;
        }
        return new Double(value);
    }

    @Override
    public Double sub(Value v) {
        double value = 0;
        if (v instanceof Double) {
            Double parsedValue = (Double) v;

            value = this.val - parsedValue.val;
        }
        return new Double(value);
    }

    @Override
    public Value mul(Value v) {
        double value = 0;
        if (v instanceof Double) {
            Double parsedValue = (Double) v;

            value = this.val * parsedValue.val;
        }
        return new Double(value);
    }

    @Override
    public Value div(Value v) {
        double value = 0;
        if (v instanceof Double) {
            Double parsedValue = (Double) v;

            value = this.val / parsedValue.val;
        }
        return new Double(value);
    }

    @Override
    public Value pow(Value v) {
        double value = 0;
        if (v instanceof Double) {
            Double parsedValue = (Double) v;

            value = (int) Math.pow(this.val, parsedValue.val);
        }
        return new Double(value);
    }

    @Override
    public boolean eq(Value v) {
        if (v instanceof Double) {
            Double parsedValue = (Double) v;
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
        if (v instanceof Double) {
            Double parsedValue = (Double) v;
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
        if (v instanceof Double) {
            Double parsedValue = (Double) v;
            if(this.val < parsedValue.val){
                return  true;
            }
            else{
                return false;
            }

        }
        return false;
    }

    @Override
    public boolean lte(Value v) {
        if (v instanceof Double) {
            Double parsedValue = (Double) v;
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
        final Double that = (Double) other;
        if (!Objects.equals(this.val, that.val)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = (37 * hash) + Objects.hashCode(this.val);
        return hash;
    }

    @Override
    public Value create(String s) {
        double value = parseDouble(s);
        return new Double(value);
    }
}
