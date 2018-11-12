package com.projekt1;

import java.util.Objects;

public class Str extends Value implements Cloneable{
    String val;

    public Str(){
        this.val = new String();
    }
    public Str(String a){
        this.val=a;
    }
    @Override
    public String toString() {
        return val;
    }

    @Override
    public Value add(Value v) {
        String value = "";
        if (v instanceof Str) {
            Str parsedValue = (Str) v;

            value = this.val + parsedValue.val;
        }
        return new Str(value);
    }

    @Override
    public Value sub(Value v) {
        return null;
    }

    @Override
    public Value mul(Value v) {
        return null;
    }

    @Override
    public Value div(Value v) {
        return null;
    }

    @Override
    public Value pow(Value v) {
        return null;
    }

    @Override
    public boolean eq(Value v) {
        if (v instanceof Str) {
            Str parsedValue = (Str) v;
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
    public boolean lte(Value v) {
        if (v instanceof Str) {
            Str parsedValue = (Str) v;
            if(this.val.length() > parsedValue.val.length()){
                return true;
            }
            else{
                return false;
            }

        }
        return false;
    }

    @Override
    public boolean gte(Value v) {
        if (v instanceof Str) {
            Str parsedValue = (Str) v;
            if(this.val.length() < parsedValue.val.length()){
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
        if (v instanceof Str) {
            Str parsedValue = (Str) v;
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
        final Str that = (Str) other;
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
        String value = s;
        return new Str(value);
    }
}
