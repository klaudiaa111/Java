package com.projekt1;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        Str s = new Str("");
        DateTime d = new DateTime();
        Integer i = new Integer(0);
        Double f = new Double(0);

        FileInputStream fstream = new FileInputStream("groupby.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));


//Read File Line By Line


//Close the input stream

        File file = new File("groupby.csv");
        ArrayList<Class<? extends Value>> arr = new ArrayList<Class<? extends Value>>();
        arr.add(Str.class);
        arr.add(DateTime.class);
        arr.add(Integer.class);
        arr.add(Double.class);




        DataFrame df = new DataFrame(new String[]{"id", "date", "total", "val"}, arr);


        ArrayList<Value> tempList1 = new ArrayList<Value>();
        tempList1.add(new Str("a"));
        tempList1.add(new Str("a"));
        tempList1.add(new Str("b"));
        tempList1.add(new Str("c"));
        tempList1.add(new Str("b"));
        ArrayList<Value> tempList2 = new ArrayList<Value>();
        tempList2.add(new DateTime(new Date(118, 10, 10)));
        tempList2.add(new DateTime(new Date(118, 10, 10)));
        tempList2.add(new DateTime(new Date(118, 10, 10)));
        tempList2.add(new DateTime(new Date(117, 07, 20)));
        tempList2.add(new DateTime(new Date(117, 07, 20)));
        ArrayList<Value> tempList3 = new ArrayList<Value>();
        tempList3.add(new Integer(5));
        tempList3.add(new Integer(12));
        tempList3.add(new Integer(10));
        tempList3.add(new Integer(129));
        tempList3.add(new Integer(6));
        ArrayList<Value> tempList4 = new ArrayList<Value>();
        tempList4.add(new Double(24.977274));
        tempList4.add(new Double(179.772924));
        tempList4.add(new Double(0.229105));
        tempList4.add(new Double(122.791720));
        tempList4.add(new Double(179.772924));

        df.getMyDatabase().put(df.colNames[0], tempList1);
        df.getMyDatabase().put(df.colNames[1], tempList2);
        df.getMyDatabase().put(df.colNames[2], tempList3);
        df.getMyDatabase().put(df.colNames[3], tempList4);
        System.out.println(df.get("id"));

        df.showDatabase();
        ArrayList<Class<? extends Value>> arr2 = new ArrayList<Class<? extends Value>>();
        arr2.add(Str.class);
        arr2.add(DateTime.class);
        arr2.add(Double.class);
        arr2.add(Double.class);


        FileInputStream fstream2 = new FileInputStream("groupby.csv");
        BufferedReader br2 = new BufferedReader(new InputStreamReader(fstream));
        File file2 = new File("groupby.csv");
        ArrayList<Class<? extends Value>> arr3 = new ArrayList<Class<? extends Value>>();
        arr3.add(Double.class);
        arr3.add(Double.class);
        arr3.add(Double.class);

//Read File Line By Line


//Close the input stream

        br2.close();

        Integer a1 = new Integer(10);
        Integer a2 = new Integer(12);
        System.out.println(a1.add(a2));
        df.adding("total", new Integer(5));
        df.showDatabase();
        ArrayList<Value> list = df.get("date");
        System.out.println(df.size());
        System.out.println(list);
        DataFrame x = df.get(new String[]{"id", "date"}, true);
        x.showDatabase();
        DataFrame y = df.iloc(1);
        y.showDatabase();
        DataFrame z = df.iloc(1, 3);
        z.showDatabase();
        Map<String, Value> row = new HashMap<>();
        row.put("id", new Str("d"));
        row.put("date", new DateTime((new Date(118, 10, 10))));
        row.put("total", new Integer(8));
        row.put("val", new Double(3.9375912));
        df.insertRow(row);
        df.showDatabase();
        df.iloc(3).showDatabase();
        df.iloc(4).showDatabase();
        Groups w = df.groupby("id");
        for(int j=0; j<w.grouped.size(); j++){
            w.grouped.get(j).showDatabase();
        }

        for(int a =0; a<w.ids.length; a++){
            System.out.println(w.ids[a]);
        }
        for(int b=0; b<w.len.length; b++){
            System.out.println(w.len[b]);
        }
        Groups k = df.groupby("total");
        for(int j=0; j<k.grouped.size(); j++){
            k.grouped.get(j).showDatabase();
        }
        Groups h = df.groupby(new String[]{"id", "total"});
        for(int j=0; j<h.grouped.size(); j++){
            h.grouped.get(j).showDatabase();
        }


        ArrayList<Class<? extends Value>> arr1 = new ArrayList<Class<? extends Value>>();
        arr1.add(Str.class);
        arr1.add(Integer.class);
        arr1.add(Double.class);

        DataFrame frame = new DataFrame(new String[]{"id", "total", "val"}, arr1);


        ArrayList<Value> tempList5 = new ArrayList<Value>();
        tempList5.add(new Str("a"));
        tempList5.add(new Str("a"));
        tempList5.add(new Str("b"));
        tempList5.add(new Str("c"));
        tempList5.add(new Str("b"));
        ArrayList<Value> tempList6 = new ArrayList<Value>();
        tempList6.add(new Integer(5));
        tempList6.add(new Integer(12));
        tempList6.add(new Integer(10));
        tempList6.add(new Integer(129));
        tempList6.add(new Integer(6));
        ArrayList<Value> tempList7 = new ArrayList<Value>();
        tempList7.add(new Double(24.977274));
        tempList7.add(new Double(179.772924));
        tempList7.add(new Double(0.229105));
        tempList7.add(new Double(122.791720));
        tempList7.add(new Double(179.772924));

        frame.getMyDatabase().put(frame.colNames[0], tempList5);
        frame.getMyDatabase().put(frame.colNames[1], tempList6);
        frame.getMyDatabase().put(frame.colNames[2], tempList7);
        frame.showDatabase();
        Groups o = frame.groupby(new String[]{"id"});
        o.sum().showDatabase();
        Value res =  frame.get("val").get(0).add(frame.get("val").get(1));
        System.out.println(res);
        System.out.println(179.772924+24.977274);
        o.mean().showDatabase();
        o.max().showDatabase();
        o.min().showDatabase();
        DateTime dt = new DateTime();
        System.out.println(dt.create("2018-12-01"));

        DataFrame data = new DataFrame("groupby.csv", arr2, true);
        System.out.println(data.size());
        data.showDatabase();
       // data.groupby("id").max().showDatabase();
        br.close();

    }
}
