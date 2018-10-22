package com.projekt1;

import java.util.Objects;

import static java.lang.Float.parseFloat;


public class Float extends Value {

    final float val;

    public Float(float a){
        this.val=a;
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }

    @Override
    public Value add(Value v) {
        float value = 0;
        if (v instanceof Float) {
            Float parsedValue = (Float) v;

            value = this.val + parsedValue.val;
        }
        return new Float(value);
    }

    @Override
    public Value sub(Value v) {
        float value = 0;
        if (v instanceof Float) {
            Float parsedValue = (Float) v;

            value = this.val - parsedValue.val;
        }
        return new Float(value);
    }

    @Override
    public Value mul(Value v) {
        float value = 0;
        if (v instanceof Float) {
            Float parsedValue = (Float) v;

            value = this.val * parsedValue.val;
        }
        return new Float(value);
    }

    @Override
    public Value div(Value v) {
        float value = 0;
        if (v instanceof Float) {
            Float parsedValue = (Float) v;

            value = this.val / parsedValue.val;
        }
        return new Float(value);
    }

    @Override
    public Value pow(Value v) {
        float value = 0;
        if (v instanceof Float) {
            Float parsedValue = (Float) v;

            value = (int) Math.pow(this.val, parsedValue.val);
        }
        return new Float(value);
    }

    @Override
    public boolean eq(Value v) {
        int a=0;
        if (v instanceof Float) {
            Float parsedValue = (Float) v;
            if(this.val == parsedValue.val){
                a=1;
            }
            else{
                a=0;
            }

        }
        if(a==1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean neq(Value v) {
        int a=0;
        if (v instanceof Float) {
            Float parsedValue = (Float) v;
            if(this.val == parsedValue.val){
                a=1;
            }
            else{
                a=0;
            }

        }
        if(a==1){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean gte(Value v) {
        int a=0;
        if (v instanceof Float) {
            Float parsedValue = (Float) v;
            if(this.val < parsedValue.val){
                a=1;
            }
            else{
                a=0;
            }

        }
        if(a==1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean lte(Value v) {
        int a=0;
        if (v instanceof Float) {
            Float parsedValue = (Float) v;
            if(this.val > parsedValue.val){
                a=1;
            }
            else{
                a=0;
            }

        }
        if(a==1){
            return true;
        }
        else{
            return false;
        }
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
        final Float that = (Float) other;
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
        float value = parseFloat(s);
        return new Float(value);
    }
}
