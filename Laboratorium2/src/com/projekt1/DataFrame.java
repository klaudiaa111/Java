package com.projekt1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


// Possible data types for the database are:
// Integer, Double, Float, String, MyCustomType

public class DataFrame {

    private Map<String, ArrayList<Object>> myDatabase;
    protected Map<String, String> colType;
    protected String [] colNames;
    protected String [] dTypes;




    /**
     * Create database with given columns names and types
     * @param columnsNames names of columns in database
     * @param dataTypes data types corresponding to columns names
     */
    public DataFrame(String [] columnsNames, String [] dataTypes) {

        myDatabase = new HashMap<String, ArrayList<Object>>();
        colType = new HashMap<String, String>();
        colNames = new String[columnsNames.length];
        dTypes = new String[dataTypes.length];

        for(int i = 0 ; i < columnsNames.length ; i++){

            ArrayList<Object> tempList = new ArrayList<Object>();

            // Initializing database: ColumnName --> ArrayList<Object>
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
    public ArrayList<Object> get(String colname){
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
        String [] colsTypes = new String[cols.length];
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
                ArrayList<Object> databaseColumn = new ArrayList<Object>();
                for(Object obj : myDatabase.get(col)){
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
            ArrayList<Object> oneByOneColumn = new ArrayList<Object>();
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
            Map<String, Object> tempMap = new HashMap<String, Object>();
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
    public boolean insertRow( Map<String, Object> row ){

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

                ArrayList<Object> databaseRow = myDatabase.get(colNames[j]);
                Object obj = databaseRow.get(i);
                String dataType = colType.get(colNames[j]);
                if(dataType.equals("string") || dataType.equals("String")){
                    String toPrint = (String) obj;
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


    public Map<String, ArrayList<Object>> getMyDatabase() {
        return myDatabase;
    }



}
