package com.projekt1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DateTime extends Value implements Cloneable{
    Date val = new Date();

    public DateTime(){
        this.val = new Date();
    }
    public DateTime(Date d){
        this.val=d;

    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String d = df.format(val);
        return d;
    }
    @Override
    public Value add(Value v) {
        return null;
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
        if (v instanceof com.projekt1.DateTime) {
            com.projekt1.DateTime parsedValue = (com.projekt1.DateTime) v;
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
        if (v instanceof com.projekt1.DateTime) {
            com.projekt1.DateTime parsedValue = (com.projekt1.DateTime) v;
            if(this.val.after(parsedValue.val)) {
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
        if (v instanceof com.projekt1.DateTime) {
            com.projekt1.DateTime parsedValue = (com.projekt1.DateTime) v;
            if(this.val.before(parsedValue.val)){
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
        if (v instanceof com.projekt1.DateTime) {
            com.projekt1.DateTime parsedValue = (com.projekt1.DateTime) v;
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
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new com.projekt1.DateTime(date);
    }
}
