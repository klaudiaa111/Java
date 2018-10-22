package com.projekt1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // First database for testing
        SparseDataFrame databaseOne = new SparseDataFrame( new String[]{"kol1", "kol2", "kol3"},
                new String[]{"int", "int", "int"}, "0");
        String [] colNamesDatabaseOne = new String[]{"kol1", "kol2", "kol3"}; // to create row to be inserted


        // -- TESTING DATABASE ONE --------------------------------------------------------------------- //
        // --------------------------------------------------------------------------------------------- //
        System.out.println("TESTING DATABASE ONE");

        Object [] rowOneDatabaseOne = new Object[]{new Integer(5), new Integer(0),
                                            new Integer(5) };
        Object [] rowTwoDatabaseOne = new Object[]{new Integer(6), new Integer(6),
                                            new Integer(0)};
        Object [] rowThreeDatabaseOne = new Object[]{new Integer(0), new Integer(7),
                                            new Integer(7) };
        Object [] rowFourDatabaseOne = new Object[]{new Integer(8), new Integer(8),
                                            new Integer(0) };

        Map<String, Object> rowOne = new HashMap<String, Object>();
        Map<String, Object> rowTwo = new HashMap<String, Object>();
        Map<String, Object> rowThree = new HashMap<String, Object>();
        Map<String, Object> rowFour = new HashMap<String, Object>();

        for(int i = 0 ; i < rowOneDatabaseOne.length ; i++){
            rowOne.put(colNamesDatabaseOne[i], rowOneDatabaseOne[i]);
            rowTwo.put(colNamesDatabaseOne[i], rowTwoDatabaseOne[i]);
            rowThree.put(colNamesDatabaseOne[i], rowThreeDatabaseOne[i]);
            rowFour.put(colNamesDatabaseOne[i], rowFourDatabaseOne[i]);
        }

        databaseOne.insertRow(rowOne);
        databaseOne.insertRow(rowTwo);
        databaseOne.insertRow(rowThree);
        databaseOne.insertRow(rowFour);
        databaseOne.showDatabase();
        System.out.println("==== Printing COOLists for DatabaseONE ====");
        databaseOne.showCOOValues();
        databaseOne.showDatabaseHidden();
        System.out.println("\n Size of the longest column is " + databaseOne.size());

        System.out.println("\nRetrieving column with name kol1");
        ArrayList<Object> objects = databaseOne.get("kol1");
        for(Object object : objects){
            System.out.print(object + " ");
        }
        System.out.println("\nRetrieving column with name kol3");
        ArrayList<Object> objectsTwo = databaseOne.get("kol3");
        for(Object object : objectsTwo){
            System.out.print(object + " ");
        }
        System.out.println("\nRetrieving column with invalid column name");
        ArrayList<Object> objectsThree = databaseOne.get("kol33");

        System.out.println("Creating deep copy of a databaseOne");
        SparseDataFrame deepCopy = databaseOne.get(new String[]{"kol1", "kol2", "kol3"}, true );
        System.out.println("Deep copy is ");
        deepCopy.showDatabase();
        deepCopy.showDatabaseHidden();

        System.out.println("Modifying deep copy ");
        Object [] rowFiveDatabaseOne = new Object[]{new Integer(99), new Integer(0),
                new Integer(99) };
        Map<String, Object> rowFive = new HashMap<String, Object>();
        for(int i = 0 ; i < rowFiveDatabaseOne.length ; i++) {
            rowFive.put(colNamesDatabaseOne[i], rowFiveDatabaseOne[i]);
        }
        deepCopy.insertRow(rowFive);

        System.out.println("\nThis is modified deep copy:");
        deepCopy.showDatabase();
        deepCopy.showDatabaseHidden();
        deepCopy.showCOOValues();
        System.out.println("\nThis is original database now:");
        databaseOne.showDatabase();
        databaseOne.showDatabaseHidden();


        System.out.println("Creating 'by value' copy");
        SparseDataFrame byValueCopy = databaseOne.get(new String[]{"kol1", "kol2", "kol3"}, false );
        System.out.println("byValueCopy copy is ");
        byValueCopy.showDatabase();

        System.out.println("Modifying by value copy ");
        Object [] rowSixDatabaseOne = new Object[]{new Integer(199), new Integer(0),
                new Integer(199) };
        Map<String, Object> rowSix = new HashMap<String, Object>();
        for(int i = 0 ; i < rowSixDatabaseOne.length ; i++) {
            rowSix.put(colNamesDatabaseOne[i], rowSixDatabaseOne[i]);
        }
        byValueCopy.insertRow(rowSix);


        System.out.println("\nThis is modified byValueCopy:");
        byValueCopy.showDatabase();
        byValueCopy.showDatabaseHidden();
        System.out.println("\nThis is original database now:");
        databaseOne.showDatabase();


        System.out.println("\nSelecting row 4 from my copiedByValue:");
        SparseDataFrame testRow = byValueCopy.iloc(4);
        testRow.showDatabase();
        testRow.showDatabaseHidden();

        System.out.println("Selecting row 2 to 4 from deep copy: ");
        SparseDataFrame rangeRows = deepCopy.iloc(2,4);
        rangeRows.showDatabase();
        rangeRows.showDatabaseHidden();

    }
}
