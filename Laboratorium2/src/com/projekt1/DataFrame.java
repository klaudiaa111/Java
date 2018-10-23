package com.projekt1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


// Possible data types for the database are:
// Integer, Double, Float, String, MyCustomType

public class DataFrame {

    private Map<String, ArrayList<Value>> myDatabase;
    protected Map<String, Value> colType;
    protected String [] colNames;
    protected Value [] dTypes;




    /**
     * Create database with given columns names and types
     * @param columnsNames names of columns in database
     * @param dataTypes data types corresponding to columns names
     */
    public DataFrame(String [] columnsNames, Value [] dataTypes) {

        myDatabase = new HashMap<String, ArrayList<Value>>();
        colType = new HashMap<String, Value>();
        colNames = new String[columnsNames.length];
        dTypes = new Value[dataTypes.length];

        for(int i = 0 ; i < columnsNames.length ; i++){

            ArrayList<Value> tempList = new ArrayList<Value>();

            // Initializing database: ColumnName --> ArrayList<Value>
            myDatabase.put(columnsNames[i], tempList);

            // Creating mapping: ColumnName --> DataType
            colType.put(columnsNames[i], dataTypes[i]);

            // Creating columnNames table
            colNames[i] = columnsNames[i];

            // Creating dataTypes table
            dTypes[i] = dataTypes[i];
        }
    }

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
        Value [] colsTypes = new Value[cols.length];
        for( int i = 0 ; i < cols.length ; i++){
            namesCol[i] = cols[i];
            colsTypes[i] = colType.get(cols[i]);
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
                Value dataType = colType.get(colNames[j]);
                if(dataType.equals("string") || dataType.equals("String")){
                    Value toPrint = (Value) obj;
                    System.out.print(toPrint);
                } else if ( dataType.equals("MyCustomType") || dataType.equals("myCustomType")){
                    MyCustomType myCustomType = (MyCustomType) obj;
                    myCustomType.showMeCustomType();
                } else {
                    System.out.print(obj);
                }

                System.out.print("      ||      ");
            }

            System.out.println("\n");

        }

    }


    public Map<String, ArrayList<Value>> getMyDatabase() {
        return myDatabase;
    }



}
