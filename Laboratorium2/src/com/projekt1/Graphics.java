package com.projekt1;
import com.projekt1.DataFrame;
import com.projekt1.DateTime;
import com.projekt1.Double;
import com.projekt1.Str;
import com.projekt1.Value;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Graphics extends Application {
    private Text actionStatus;
    private Stage savedStage;
    DataFrame d;
    File selectedFile;
    String[] arr;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button open = new Button("Open");

        Button min = new Button("Min");

        Button max = new Button("Max");

        Button sum = new Button("Sum");

        Button mean = new Button("Mean");

        Button var = new Button("Var");

        Button std = new Button("Std");

        Label name = new Label("Groupby:");
        CheckBox cb1 = new CheckBox("id");
        CheckBox cb2 = new CheckBox("date");
        CheckBox cb3 = new CheckBox("total");
        CheckBox cb4 = new CheckBox("val");
        Button ok = new Button("OK");
        VBox checkbox = new VBox(30);
        checkbox.getChildren().addAll(name, cb1, cb2, cb3, cb4, ok);

        Button create = new Button("Create a chart");
        StackPane cr = new StackPane();
        cr.getChildren().add(create);


        actionStatus = new Text();
        VBox buttons = new VBox(30);
        buttons.getChildren().addAll(min, max, sum, mean, var, std, actionStatus);

        VBox result = new VBox(30);
        Label resultLabel = new Label("Wynik:");
        // Dodajemy ją do VBox-u
        result.getChildren().add(resultLabel);
        // Tworzymy TextArea do wypisywania wyniku
        TextArea wynikTextArea = new TextArea();
        // Wyłączamy możliwość edycji
        wynikTextArea.setEditable(false);
        // Włączamy zawijanie tekstu
        wynikTextArea.setWrapText(true);
        // Dodajemy do VBox-u
        result.getChildren().add(wynikTextArea);

        HBox hbox = new HBox(100);
        hbox.getChildren().addAll(checkbox, buttons);

        VBox all = new VBox(30);
        all.getChildren().addAll(open, hbox, cr, result);

        Scene scene = new Scene(all, 800, 850);
        primaryStage.setScene(scene);
        primaryStage.show();
        savedStage = primaryStage;

        //second stage
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        LineChart lineChart = new LineChart(xAxis, yAxis);
        ChoiceBox choice1 = new ChoiceBox(FXCollections.observableArrayList(
                "id", "date", "total", "val")
        );
        ChoiceBox choice2 = new ChoiceBox(FXCollections.observableArrayList(
                "id", "date", "total", "val")
        );
        Button draw = new Button("Draw!");
        HBox hb = new HBox(30);
        hb.getChildren().addAll(choice1, choice2);

        VBox all2 = new VBox(30);
        all2.getChildren().addAll(hb, draw);
        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series


        Scene secondScene = new Scene(all2, 230, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        ok.setOnAction(new EventHandler<ActionEvent>() {

            List<String> group = new ArrayList<String>();
            @Override
            public void handle(ActionEvent event) {
                if(cb1.isSelected()){
                    group.add("id");
                }
                else if(cb2.isSelected()){
                    group.add("date");
                }
                else if(cb3.isSelected()){
                    group.add("total");
                }
                else if(cb4.isSelected()){
                    group.add("val");
                }
                arr = new String[group.size()];
                group.toArray(arr);

            }
        });

        draw.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                newWindow.setTitle("Chart");
                //defining the axes

                final CategoryAxis xAxis = new CategoryAxis();
                final CategoryAxis yAxis = new CategoryAxis();
                xAxis.setLabel(choice1.getValue().toString());
                yAxis.setLabel(choice2.getValue().toString());
                //creating the chart
                final LineChart<String, String> lineChart = new LineChart<String, String>(xAxis,yAxis);

                lineChart.setTitle("Chart");
                //defining a series
                XYChart.Series series = new XYChart.Series();
                series.setName("Points");
                //populating the series with data
                for(int i=0; i<d.size(); i++){
                    series.getData().add(new XYChart.Data (d.get(choice1.getValue().toString()).get(i).toString(), d.get(choice2.getValue().toString()).get(i).toString()));
                }

                Scene scene  = new Scene(lineChart,800,600);
                lineChart.getData().add(series);

                newWindow.setScene(scene);
                newWindow.show();
            }
        });

        create.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {


                newWindow.show();
            }
        });


        open.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                FileChooser fileChooser = new FileChooser();
                File selectedFile = fileChooser.showOpenDialog(null);

                if (selectedFile != null) {

                    actionStatus.setText("File selected: " + selectedFile.getName());
                    ArrayList<Class<? extends Value>> arr2 = new ArrayList<Class<? extends Value>>();
                    arr2.add(Str.class);
                    arr2.add(DateTime.class);
                    arr2.add(com.projekt1.Double.class);
                    arr2.add(Double.class);
                    d = new DataFrame(selectedFile.getName(), arr2, true);
                    wynikTextArea.setText("Wycztano plik " + selectedFile.getName()+"\n");

                }
                else{
                    System.out.println("Błąd");
                }
            }
        });
        min.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                wynikTextArea.setText("====Min====" + "\n");
                                DataFrame df = d.groupby(arr).min();
                                for (String col : df.colNames) {
                                    wynikTextArea.appendText(col + "   ||   ");
                                }

                                wynikTextArea.appendText("\n");

                                for (int i = 0; i < df.get(df.colNames[0]).size(); i++) {
                                    for (int j = 0; j < df.colNames.length; j++) {

                                        ArrayList<Value> databaseRow = df.get(df.colNames[j]);
                                        Value obj = databaseRow.get(i);
                                        String o = obj.toString();
                                        Class<? extends Value> dataType = df.colType.get(df.colNames[j]);

                                        wynikTextArea.appendText(o + "      ||      ");
                                    }
                                    wynikTextArea.appendText("\n");

                                }
                            }
                        });

        max.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                wynikTextArea.setText("====Max====" + "\n");
                DataFrame df = d.groupby(arr).max();
                for (String col : df.colNames) {
                    wynikTextArea.appendText(col + "   ||   ");
                }

                wynikTextArea.appendText("\n");

                for (int i = 0; i < df.get(df.colNames[0]).size(); i++) {
                    for (int j = 0; j < df.colNames.length; j++) {

                        ArrayList<Value> databaseRow = df.get(df.colNames[j]);
                        Value obj = databaseRow.get(i);
                        String o = obj.toString();
                        Class<? extends Value> dataType = df.colType.get(df.colNames[j]);

                        wynikTextArea.appendText(o + "      ||      ");
                    }
                    wynikTextArea.appendText("\n");

                }

            }
        });
        mean.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                wynikTextArea.setText("====Mean====" + "\n");
                DataFrame df = d.groupby(arr).mean();
                for (String col : df.colNames) {
                    wynikTextArea.appendText(col + "   ||   ");
                }

                wynikTextArea.appendText("\n");

                for (int i = 0; i < df.get(df.colNames[0]).size(); i++) {
                    for (int j = 0; j < df.colNames.length; j++) {

                        ArrayList<Value> databaseRow = df.get(df.colNames[j]);
                        Value obj = databaseRow.get(i);
                        String o = obj.toString();
                        Class<? extends Value> dataType = df.colType.get(df.colNames[j]);

                        wynikTextArea.appendText(o + "      ||      ");
                    }
                    wynikTextArea.appendText("\n");

                }
            }
        });
        sum.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                wynikTextArea.setText("====Sum====" + "\n");
                DataFrame df = d.groupby(arr).sum();
                for (String col : df.colNames) {
                    wynikTextArea.appendText(col + "   ||   ");
                }

                wynikTextArea.appendText("\n");

                for (int i = 0; i < df.get(df.colNames[0]).size(); i++) {
                    for (int j = 0; j < df.colNames.length; j++) {

                        ArrayList<Value> databaseRow = df.get(df.colNames[j]);
                        Value obj = databaseRow.get(i);
                        String o = obj.toString();
                        Class<? extends Value> dataType = df.colType.get(df.colNames[j]);

                        wynikTextArea.appendText(o + "      ||      ");
                    }
                    wynikTextArea.appendText("\n");

                }
            }
        });
        var.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                wynikTextArea.setText("====Var====" + "\n");
                DataFrame df = d.groupby(arr).var();
                for (String col : df.colNames) {
                    wynikTextArea.appendText(col + "   ||   ");
                }

                wynikTextArea.appendText("\n");

                for (int i = 0; i < df.get(df.colNames[0]).size(); i++) {
                    for (int j = 0; j < df.colNames.length; j++) {

                        ArrayList<Value> databaseRow = df.get(df.colNames[j]);
                        Value obj = databaseRow.get(i);
                        String o = obj.toString();
                        Class<? extends Value> dataType = df.colType.get(df.colNames[j]);

                        wynikTextArea.appendText(o + "      ||      ");
                    }
                    wynikTextArea.appendText("\n");

                }
            }
        });

        std.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                wynikTextArea.setText("====Std====" + "\n");
                DataFrame df = d.groupby(arr).std();
                for (String col : df.colNames) {
                    wynikTextArea.appendText(col + "   ||   ");
                }

                wynikTextArea.appendText("\n");

                for (int i = 0; i < df.get(df.colNames[0]).size(); i++) {
                    for (int j = 0; j < df.colNames.length; j++) {

                        ArrayList<Value> databaseRow = df.get(df.colNames[j]);
                        Value obj = databaseRow.get(i);
                        String o = obj.toString();
                        Class<? extends Value> dataType = df.colType.get(df.colNames[j]);

                        wynikTextArea.appendText(o + "      ||      ");
                    }
                    wynikTextArea.appendText("\n");

                }
            }
        });
    }

}



