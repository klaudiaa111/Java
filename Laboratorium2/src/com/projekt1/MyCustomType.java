package com.projekt1;


public class MyCustomType {
    private int customOne;
    private String customTwo;

    public MyCustomType(int customOne, String customTwo) {
        this.customOne = customOne;
        this.customTwo = customTwo;
    }

    public void showMeCustomType(){
        System.out.print(" " + customOne + ": " + customTwo);
    }


}
