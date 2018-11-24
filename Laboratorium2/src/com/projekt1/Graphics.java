package com.projekt1;
import com.projekt1.DataFrame;
import com.projekt1.DateTime;
import com.projekt1.Double;
import com.projekt1.Str;
import com.projekt1.Value;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class Graphics extends Application {
    private Text actionStatus;
    private Stage savedStage;
    DataFrame d;
    File selectedFile;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button open = new Button("Open");
        HBox open1 = new HBox(10);
        open1.getChildren().addAll(open);
        Button min = new Button("Min");
        HBox min1 = new HBox(10);
        min1.getChildren().addAll(min);
        Button max = new Button("Max");
        HBox max1 = new HBox(10);
        max1.getChildren().addAll(max);
        Button sum = new Button("Sum");
        HBox sum1 = new HBox(10);
        sum1.getChildren().addAll(sum);
        Button mean = new Button("Mean");
        HBox mean1 = new HBox(10);
        mean1.getChildren().addAll(mean);





        actionStatus = new Text();
        VBox vbox = new VBox(30);
        Label wynikLabel = new Label("Wynik:");
        // Dodajemy ją do VBox-u
        vbox.getChildren().add(wynikLabel);
        // Tworzymy TextArea do wypisywania wyniku
        TextArea wynikTextArea = new TextArea();
        // Wyłączamy możliwość edycji
        wynikTextArea.setEditable(false);
        // Włączamy zawijanie tekstu
        wynikTextArea.setWrapText(true);
        // Dodajemy do VBox-u
        vbox.getChildren().add(wynikTextArea);




        vbox.getChildren().addAll( open1,min1, max1, sum1, mean1, actionStatus);
        Scene scene = new Scene(vbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        savedStage = primaryStage;

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
                                DataFrame df = d.groupby("id").min();
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
                DataFrame df = d.groupby("id").max();
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
                DataFrame df = d.groupby("id").mean();
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
                DataFrame df = d.groupby("id").sum();
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



