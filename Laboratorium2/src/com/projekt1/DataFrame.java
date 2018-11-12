package com.projekt1;


import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

// Possible data types for the database are:
// Integer, Double, Float, String, MyCustomType

public class DataFrame {

    private Map<String, ArrayList<Value>> myDatabase;
    protected Map<String, Class<? extends Value>> colType;
    protected String [] colNames;
    protected ArrayList<Class<? extends Value>> dTypes;




    /**
     * Create database with given columns names and types
     * @param columnsNames names of columns in database
     * @param dataTypes data types corresponding to columns names
     */

    public DataFrame(String [] columnsNames, ArrayList<Class<? extends Value>> dataTypes) {

        myDatabase = new HashMap<String, ArrayList<Value>>();
        colType = new HashMap<String, Class<? extends Value>>();
        colNames = new String[columnsNames.length];
        dTypes = new ArrayList<>();

        for(int i = 0 ; i < columnsNames.length ; i++){

            ArrayList<Value> tempList = new ArrayList<Value>();

            // Initializing database: ColumnName --> ArrayList<Value>
            myDatabase.put(columnsNames[i], tempList);

            // Creating mapping: ColumnName --> DataType
            colType.put(columnsNames[i], dataTypes.get(i));

            // Creating columnNames table
            colNames[i] = columnsNames[i];

            // Creating dataTypes table
            dTypes.add(dataTypes.get(i));
        }
    }

    public DataFrame(String filename, ArrayList<Class<? extends Value>> dataTypes, boolean header){

        myDatabase = new HashMap<String, ArrayList<Value>>();
        colType = new HashMap<String, Class<? extends Value>>();
        colNames = new String[dataTypes.size()];
        dTypes = new ArrayList<>();
        String [] colHeader;
        String [] values;

        try{
            System.out.println(filename);
            FileInputStream fstream = new FileInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;

            strLine = br.readLine();
            System.out.println(strLine);

            colHeader = strLine.split(",");



            for(int i = 0 ; i < dataTypes.size() ; i++){

                ArrayList<Value> tempList = new ArrayList<Value>();

                // Initializing database: ColumnName --> ArrayList<Object>
                myDatabase.put(colHeader[i], tempList);

                // Creating mapping: ColumnName --> DataType
                colType.put(colHeader[i], dataTypes.get(i));

                // Creating columnNames table
                colNames[i] = colHeader[i];

                // Creating dataTypes table
                dTypes.add(dataTypes.get(i));
            }


            //  Read File Line By Line
            int counter = 0;
            while ((strLine = br.readLine()) != null) {
                strLine = br.readLine();
                values = strLine.split(",");

                Map<String, Value> myNewRow = new HashMap<String, Value>();

                for(int i = 0 ; i < values.length ; i++){


                    if(dTypes.get(i).equals(Integer.class)){
                        Value obj = new Integer(parseInt(values[i]));
                        myNewRow.put(colNames[i], obj);
                    } else if (dTypes.get(i).equals(Double.class)){
                        Value obj = new Double(parseDouble(values[i]));
                        myNewRow.put(colNames[i], obj);
                    } else if (dTypes.get(i).equals(Float.class)){
                        Value obj = new Float(parseFloat(values[i]));
                        myNewRow.put(colNames[i], obj);
                    } else if (dTypes.get(i).equals(Str.class)){
                        Value obj = new Str(values[i]);
                        myNewRow.put(colNames[i], obj);
                    } else if (dTypes.get(i).equals(DateTime.class)){
                        DateTime d = new DateTime();
                        DateTime obj = (DateTime) d.create(values[i]);
                        myNewRow.put(colNames[i], obj);

                    }

                }

                this.insertRow(myNewRow);

                counter++;

            }



            br.close();
        }
        catch(FileNotFoundException exn){
            System.out.println("File not found");
        }
        catch(IOException ioex){
            System.out.println("Reading exception");
        }

    }
/*

    public DataFrame(String fileName, ArrayList<Class<? extends Value>> types, boolean header, String[] names) {
        String line = "";
        String cvsSplitBy = ",";
        colType = new HashMap<String, Class<? extends Value>>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int counter = 0;
            if (header) {
                line = br.readLine();
                String[] col = line.split(cvsSplitBy);
                for (int i = 0; i < types.size(); i++) {
                    colType.put(col[i], types.get(i));
                }
            }
            else {
                for (int i = 0; i < types.size(); i++) {
                    colType.put(names[i], types.get(i));
                }
            }

            while ((line = br.readLine()) != null) {
                String[] col = line.split(cvsSplitBy);
                Map<String, Value> objs = new HashMap<>();
                if (header && counter != 0) {
                    for (int i = 1; i < types.size()-2; i++) {
                        if (types.get(i) == Integer.class) {
                            Integer c = new Integer(parseInt(col[i]));
                            objs.put(names[i], c);
                        } else if (types.get(i) == Double.class) {
                            Double c = new Double(parseDouble(col[i]));
                            objs.put(names[i], c);
                        } else if (types.get(i) == Float.class) {
                            Float c = new Float(parseFloat(col[i]));
                            objs.put(names[i], c);
                        } else if (types.get(i) == Str.class) {
                            Str c = new Str(col[i]);
                            objs.put(names[i], c);
                        }
                        else if (types.get(i) == DateTime.class) {
                            DateTime d = new DateTime();
                            DateTime c = (DateTime) d.create(col[i]);
                            objs.put(names[i], c);
                        }
                    }
                    try {
                        insertRow(objs);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if (!header) {
                    for (int i = 1; i < types.size()-2; i++) {
                        if (types.get(i) == Integer.class) {
                            Integer c = new Integer(parseInt(col[i]));
                            objs.put(names[i], c);
                        } else if (types.get(i) == Double.class) {
                            Double c = new Double(parseDouble(col[i]));
                            objs.put(names[i], c);
                        } else if (types.get(i) == Float.class) {
                            Float c = new Float(parseFloat(col[i]));
                            objs.put(names[i], c);
                        } else if (types.get(i) == Str.class) {
                            Str c = new Str(col[i]);
                            objs.put(names[i], c);
                        }
                        else if (types.get(i) == DateTime.class) {
                            DateTime d = new DateTime();
                            DateTime c = (DateTime) d.create(col[i]);
                            objs.put(names[i], c);
                        }
                    }
                    try {
                        insertRow(objs);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                counter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


*/



    /**
     * returns number of rows in a database
     * @return number of rows in a database
     */
    public int size(){
        System.out.println("==== Returning size ====");
        return myDatabase.get(colNames[0]).size();
    }

    /**
     * returns column of a given name
     * @param colname name of wanted column
     * @return ArrayList<Object> as a column of a database
     */
    public ArrayList<Value> get(String colname){
        Set<String> mySet = myDatabase.keySet();
        if(!mySet.contains(colname)){
            System.out.println("Invalid column name. Check your data");
            return  null;
        }
        System.out.println("==== Returning column of given name ====");
        return myDatabase.get(colname);
    }

    /**
     * Returns new database created basing on this database,
     * copies by reference or value depending on 'copy'
     * @param cols names of columns to be copied
     * @param copy specify copying by reference ( true ) or value ( false )
     * @return
     */
    public DataFrame get(String cols[], boolean copy){

        System.out.println("==== Getting new Frame ====");
        String [] namesCol = new String[cols.length];
        ArrayList<Class<? extends Value>> colsTypes = new ArrayList<Class<? extends Value>>();
        for( int i = 0 ; i < cols.length ; i++){
            namesCol[i] = cols[i];
            colsTypes.add(colType.get(cols[i]));
        }


        DataFrame newFrame = new DataFrame(namesCol, colsTypes);
        if(copy){
            for(String col : cols){
                newFrame.getMyDatabase().put(col, myDatabase.get(col));
            }

            return newFrame;
        }else{
            for(String col : cols){
                ArrayList<Value> databaseColumn = new ArrayList<Value>();
                for(Value obj : myDatabase.get(col)){
                    databaseColumn.add(obj);
                }
                newFrame.getMyDatabase().put(col, databaseColumn);
            }
            return newFrame;
        }
    }


    public DataFrame iloc(int i){

        System.out.println("==== Selecting row number " + i + " ==== ");

        if(i > myDatabase.get(colNames[0]).size() || i < 1){
            System.out.println("Index out of bounds. Check your data.");
            return null;
        }

        DataFrame dataFrame = new DataFrame(colNames, dTypes);

        for(String col : colNames){
            ArrayList<Value> oneByOneColumn = new ArrayList<Value>();
            oneByOneColumn.add(myDatabase.get(col).get(i-1));
            dataFrame.getMyDatabase().put(col, oneByOneColumn);
        }

        return dataFrame;
    }


    public DataFrame iloc(int from, int to){
        if(to <= from || from < 1 || to > myDatabase.get(colNames[0]).size()){
            System.out.println("Invalid indexes. Check your data");
            return null;
        }

        System.out.println("==== Selecting rows " + from + " to " + to + "====");

        DataFrame dataFrame = new DataFrame(colNames, dTypes);

        for(int i = from ; i <= to ; i++){

            DataFrame tempFrame = this.iloc(i);
            Map<String, Value> tempMap = new HashMap<String, Value>();
            for(String col : colNames){
                tempMap.put(col, tempFrame.get(col).get(0));
            }
            dataFrame.insertRow(tempMap);
        }

        return dataFrame;
    }

    /**
     * Insert a row of data into the database
     * @param row map representing ( column, value ) pairs to be inserted
     */
    public boolean insertRow( Map<String, Value> row ){

        // Valid size of inserting data
        if(row.size() != myDatabase.size()){
            System.out.println("Inserting row has invalid size. Check your data.");
            return false;
        }

        // Valid columns of inserting data
        Set<String> databaseKeys = myDatabase.keySet();
        Set<String> insertingKeys = row.keySet();
        for( String test : databaseKeys){
            if(!insertingKeys.contains(test)){
                System.out.println("Inserting row has invalid columns names. Check your data.");
                return false;
            }
        }

        // Insert data
        System.out.println("===== Inserting row =====");
        for( String cName : colNames){
            myDatabase.get(cName).add( row.get(cName) );
        }

        return true;

    }


    /**
     * The method prints data stored in a database
     */
    public void showDatabase(){

        System.out.println("\n ===== Printing database =====");

        for(String col : colNames){
            System.out.print(col);
            System.out.print("   ||   ");
        }

        System.out.println("\n");

        for(int i = 0 ; i < myDatabase.get(colNames[0]).size() ; i++){
            for(int j = 0 ; j < colNames.length ; j++){

                ArrayList<Value> databaseRow = myDatabase.get(colNames[j]);
                Value obj = databaseRow.get(i);
                String o = obj.toString();
                Class<? extends Value> dataType = colType.get(colNames[j]);

                    System.out.print(o);

                System.out.print("      ||      ");
            }
            System.out.println("\n");

        }

    }


    public Map<String, ArrayList<Value>> getMyDatabase() {
        return myDatabase;
    }

    public Groups groupby(String colname){
        Groups g = new Groups();
        LinkedList<DataFrame> group = new LinkedList<>();
        g.ids = new String[1];
        g.ids[0] = colname;
        int size = 0;

        for(int i = 0; i<myDatabase.get(colname).size(); ++i){
            System.out.println("Number");
            System.out.println(i);
            Value value = myDatabase.get(colname).get(i);
            boolean added = false;
            int length = 0;
            for(DataFrame d : group){

                if(d.get(colname).get(0).equals(value)){
                    added = true;

                    for(String name : d.colNames) {
                        Value v = myDatabase.get(name).get(i);
                        d.get(name).add(v);
                        length++;
                    }
                }
            }

            if(added == false){
                System.out.println(i+1);
                group.add(iloc(i+1));
                size++;
                length++;
            }
        }

        g.len = new int[size];
        int i = 0;
        for(DataFrame df : group){
            g.len[i] = df.myDatabase.get(df.colNames[0]).size();
            i++;
        }
        g.grouped = group;
        return g;
    }
    public Groups groupby(String[] colnames){
        LinkedList<DataFrame> temp = this.groupby(colnames[0]).grouped;
        Groups result = new Groups();
        result.grouped = new LinkedList<>();

        int s = 0;
        if(colnames.length > 1){
            s = 1;
        }

        for(int i = s; i < colnames.length; i++){
            for(DataFrame d : temp){
                LinkedList<DataFrame> groups = d.groupby(colnames[i]).grouped;
                for(DataFrame df: groups) {
                    result.grouped.add(df);
                }
            }
        }
        int index = 0;
        result.len = new int[result.grouped.size()];
        for(DataFrame gr : result.grouped){
            result.len[index] = gr.size();
            index++;
        }
        result.ids = colnames;
        return result;
    }
    public DataFrame adding(String colname, Value v){
        ArrayList<Value> tempList = new ArrayList<Value>();
        Set<String> mySet = myDatabase.keySet();
        if(!mySet.contains(colname)){
            System.out.println("Invalid column name. Check your data");
            return  null;
        }
        try {
            for (Value val : myDatabase.get(colname)) {
                tempList.add(val.add(v));
            }
            myDatabase.replace(colname, tempList);
        }
        catch(IllegalArgumentException e){
            System.out.println("Another classes cannot be added");
            return null;
            }
            return this;
        }

    public DataFrame multiplication(String colname, Value v){
        ArrayList<Value> tempList = new ArrayList<Value>();
        Set<String> mySet = myDatabase.keySet();
        if(!mySet.contains(colname)){
            System.out.println("Invalid column name. Check your data");
            return  null;
        }
        try {
            for (Value val : myDatabase.get(colname)) {
                tempList.add(val.mul(v));
            }
            myDatabase.replace(colname, tempList);
        }
        catch(IllegalArgumentException e){
            System.out.println("Another classes cannot be added");
            return null;
        }
        return this;
    }
    public DataFrame division(String colname, Value v){
        ArrayList<Value> tempList = new ArrayList<Value>();
        Set<String> mySet = myDatabase.keySet();
        if(!mySet.contains(colname)){
            System.out.println("Invalid column name. Check your data");
            return  null;
        }
        try {
            for (Value val : myDatabase.get(colname)) {
                tempList.add(val.mul(v));
            }
            myDatabase.replace(colname, tempList);
        }
        catch(IllegalArgumentException e){
            System.out.println("Another classes cannot be added");
            return null;
        }
        return this;
    }

    }
