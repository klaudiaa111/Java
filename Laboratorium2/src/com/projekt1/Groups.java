package com.projekt1;

import javax.xml.crypto.Data;
import java.util.*;

public class Groups implements Groupby{
    LinkedList<DataFrame> grouped;
    String[] ids;
    int len[];

    public Groups(){
        LinkedList<DataFrame> grouped = new LinkedList<>();
        String[] ids = new String[]{};
        int len[] = new int[]{};
    }
    public Groups(DataFrame data, String[] name){

    }

    @Override
    public DataFrame max() {
        DataFrame df = new DataFrame(grouped.get(0).colNames, grouped.get(0).dTypes);
        for(DataFrame d : grouped){
            for(String s : d.colNames){
                if(!Arrays.asList(ids).contains(s)){
                    Value v = d.get(s).get(0);
                    for(int i=1; i<d.size(); i++){
                        if(v.gte(d.get(s).get(i))) {
                            v = d.get(s).get(i);
                        }
                    }
                        df.get(s).add(v);
                }
                else{
                    df.get(s).add(d.get(s).get(0));
                }
            }

        }
        System.out.println();
        System.out.println("==== Max ====");
        return df;
    }

    @Override
    public DataFrame min() {
        DataFrame df = new DataFrame(grouped.get(0).colNames, grouped.get(0).dTypes);
        for(DataFrame d : grouped){
            for(String s : d.colNames){
                if(!Arrays.asList(ids).contains(s)){
                    Value v = d.get(s).get(0);
                    for(int i=1; i<d.size(); i++){
                        if(v.lte(d.get(s).get(i))) {
                            v = d.get(s).get(i);
                        }
                    }
                    df.get(s).add(v);
                }
                else{
                    df.get(s).add(d.get(s).get(0));
                }
            }

        }
        System.out.println();
        System.out.println("==== Min ====");
        return df;
    }


    @Override
    public DataFrame mean() {

        DataFrame df = new DataFrame(grouped.get(0).colNames, grouped.get(0).dTypes);
        for(DataFrame d : grouped){
            for(String s : d.colNames){
                if(!Arrays.asList(ids).contains(s)){
                    Value v = d.get(s).get(0);
                    for(int i=1; i<d.size(); i++){
                        v = v.add(d.get(s).get(i));
                    }
                    if(v.getClass()==Integer.class) {
                        df.get(s).add(v.div(new Integer(d.size())));
                    }
                    else if(v.getClass()==Float.class){
                        df.get(s).add(v.div(new Float(d.size())));
                    }
                    else if(v.getClass()==Double.class){
                        df.get(s).add(v.div(new Double(d.size())));
                    }
                }
                else{
                    df.get(s).add(d.get(s).get(0));
                }
            }

        }
        System.out.println();
        System.out.println("==== Mean ====");
        return df;
    }

    @Override
    public DataFrame std() {
        return null;
    }

    @Override
    public DataFrame sum() {
        DataFrame df = new DataFrame(grouped.get(0).colNames, grouped.get(0).dTypes);
        for(DataFrame d : grouped){
            for(String s : d.colNames){
                if(!Arrays.asList(ids).contains(s)){
                    Value v = d.get(s).get(0);
                    for(int i=1; i<d.size(); i++){
                        v = v.add(d.get(s).get(i)) ;
                    }
                    df.get(s).add(v);
                }
                else{
                    df.get(s).add(d.get(s).get(0));
                }
            }

        }
        System.out.println();
        System.out.println("==== Sum ====");
        return df;
    }

    @Override
    public DataFrame var() {
        return null;
    }

    @Override
    public DataFrame apply(Applayable a) {
        return null;
    }
}
