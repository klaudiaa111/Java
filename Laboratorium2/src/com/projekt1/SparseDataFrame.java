package com.projekt1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SparseDataFrame extends  DataFrame {

    private Map<String, ArrayList<COOValue>> myValidData;
    private Value hide;

    /**
     * Create database with given columns names and types
     *
     * @param columnsNames names of columns in database
     * @param dataTypes    data types corresponding to columns names
     */
    public SparseDataFrame(String[] columnsNames, ArrayList<Class<? extends Value>> dataTypes, Value hide) {
        super(columnsNames, dataTypes);
        this.hide = hide;
        myValidData = new HashMap<String, ArrayList<COOValue>>();

        for(int i = 0 ; i < columnsNames.length ; i++) {

            ArrayList<COOValue> tempListCOO = new ArrayList<COOValue>();

            // Initializing COO lists: ColumnName --> ArrayList<COOValue>
            myValidData.put(columnsNames[i], tempListCOO);

        }
    }

    /**
     * returns size of the longest column ( after hiding values )
     * @return size of the longest column in a database
     */
    @Override
    public int size() {
        System.out.println("==== Returning size Sparse ====");
        int max = 0;
        for(String col : colNames){
            if( myValidData.get(col).size() > max  ){
                max = myValidData.get(col).size();
            }
        }
        return  max;
    }

    @Override
    public ArrayList<Value> get(String colname) {
        Set<String> mySet = myValidData.keySet();
        if(!mySet.contains(colname)){
            System.out.println("Invalid column name. Check your data");
            return  null;
        }
        System.out.println("==== Returning column of given name - SparseDataFrame ====");
        ArrayList<Value> objArr = new ArrayList<Value>();
        for(COOValue cooValue : myValidData.get(colname)){
            objArr.add(cooValue.getValueOfIndex());
        }
        return objArr;
    }


    @Override
    public SparseDataFrame get(String[] cols, boolean copy) {
        System.out.println("==== Getting new Frame ====");
        String [] namesCol = new String[cols.length];
        ArrayList<Class<? extends Value>> colsTypes = new ArrayList<Class<? extends Value>>();
        for( int i = 0 ; i < cols.length ; i++){
            namesCol[i] = cols[i];
            colsTypes.add(colType.get(cols[i]));
        }

        SparseDataFrame newFrame = new SparseDataFrame(namesCol, colsTypes, hide);
        if(copy){
            for(String col : cols){
                newFrame.getMyDatabase().put(col, super.getMyDatabase().get(col));
                newFrame.getMyValidData().put(col, this.myValidData.get(col));
            }
            return newFrame;
        }else{
            for(String col : cols){
                ArrayList<Value> databaseColumn = new ArrayList<Value>();
                ArrayList<COOValue> myValidColumn = new ArrayList<COOValue>();
                for(Value obj : super.getMyDatabase().get(col)){
                    databaseColumn.add(obj);
                }
                for(COOValue cooValue : this.myValidData.get(col)){
                    myValidColumn.add(cooValue);
                }
                newFrame.getMyDatabase().put(col, databaseColumn);
                newFrame.getMyValidData().put(col, myValidColumn);
            }
            return newFrame;
        }
    }

    @Override
    public SparseDataFrame iloc(int i) {

        System.out.println("==== Selecting row number " + i + " ==== ");

        if(i > super.getMyDatabase().get(colNames[0]).size() || i < 1){
            System.out.println("Index out of bounds. Check your data.");
            return null;
        }

        SparseDataFrame sparseDataFrame = new SparseDataFrame(colNames, dTypes, hide);

        for(String col : colNames){

            ArrayList<Value> oneByOneColumn = new ArrayList<Value>();

                Value hiddenValue = hide;
                Value insertedValue = super.getMyDatabase().get(col).get(i-1);
                if(!hiddenValue.equals(insertedValue)){
                    COOValue cooValue = new COOValue(0, insertedValue);
                    sparseDataFrame.getMyValidData().get(col).add(cooValue);

            }
            oneByOneColumn.add(super.getMyDatabase().get(col).get(i-1));
            sparseDataFrame.getMyDatabase().put(col, oneByOneColumn);

        }

        return sparseDataFrame;
    }


    @Override
    public SparseDataFrame iloc(int from, int to) {

        if(to <= from || from < 1 || to > super.getMyDatabase().get(colNames[0]).size()){
            System.out.println("Invalid indexes. Check your data");
            return null;
        }

        System.out.println("==== Selecting rows " + from + " to " + to + "====");

        SparseDataFrame sparseDataFrame = new SparseDataFrame(colNames, dTypes, hide);

        for(int i = from ; i <= to ; i++){

            System.out.println(i);
            SparseDataFrame sparseDataFrameTemp = this.iloc(i);
            sparseDataFrameTemp.showDatabase();
            Map<String, Value> tempMap = new HashMap<String, Value>();
            for(String col : colNames){
                System.out.println("aaaaaaaaaa");
                tempMap.put(col, sparseDataFrameTemp.getMyDatabase().get(col).get(0));
            }
            System.out.println("aaa");
            sparseDataFrame.insertRow(tempMap);
            System.out.println("bbb");
        }

        return sparseDataFrame;

    }


    @Override
    public boolean insertRow(Map<String, Value> row) {


        if(super.insertRow(row)){

            for(String col : super.colNames){

                    Value hiddenValue = hide;
                    Value insertedValue = row.get(col);
                    if(!hiddenValue.equals(insertedValue)){
                        COOValue cooValue = new COOValue(super.size()-1, insertedValue);
                        myValidData.get(col).add(cooValue);
                    }
            }
            return true;
        }
        else{
            return false;
        }

    }


    public void showCOOValues(){
        for(String col : super.colNames){
            for(COOValue cooValue : myValidData.get(col)){
                System.out.print(cooValue.toString() + " ");
            }
            System.out.println("\n");
        }
    }


    public void showDatabaseHidden() {

        System.out.println("===== Printing database without hidden values =====");

        for(String col : colNames){
            System.out.print(col);
            System.out.print("   ||   ");
        }

        System.out.println("\n");

        for(int i = 0 ; i < super.getMyDatabase().get(colNames[0]).size() ; i++){
            for(int j = 0 ; j < colNames.length ; j++){


                if( i >= myValidData.get(colNames[j]).size()){
                    continue;
                }
                if(myValidData.get(colNames[j]).get(i) != null){
                    Object obj = super.getMyDatabase().get(colNames[j]).get(myValidData.get(colNames[j]).get(i).getIndex());

                    Class<? extends Value> dataType = colType.get(colNames[j]);
                    if(dataType.equals("string") || dataType.equals("String")){
                        String toPrint = (String) obj;
                        System.out.print(toPrint);
                    } else if ( dataType.equals("MyCustomType") || dataType.equals("myCustomType")){
                        MyCustomType myCustomType = (MyCustomType) obj;
                        myCustomType.showMeCustomType();
                    } else {
                        System.out.print(obj);
                    }

                }
                else{
                    System.out.println(" ");
                }

                System.out.print("      ||      ");
            }

            System.out.println("\n");

        }


    }



    public Map<String, ArrayList<COOValue>> getMyValidData() {
        return myValidData;
    }
}
