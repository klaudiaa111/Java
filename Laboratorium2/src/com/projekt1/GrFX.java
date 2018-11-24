package com.projekt1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class GrFX extends Application {
    private Text actionStatus;
    DataFrame d;

    @Override
    public void start(Stage primaryStage) {
        // Tworzymy VBox, który będzie porządkował elementy w pionie
        // Argument liczbowy (tu 5) oznacza odstepy między elementami
        VBox siatkaPionowaVBox = new VBox(5);
        // Ustawiamy odstęp pomiędzy krawędziami wewnętrznymi VBox-u
        // a elementami w jego wnętrzu
        siatkaPionowaVBox.setPadding(new Insets(5));
        // Etykieta to pierwszego pola
        HBox buttonHBox = new HBox(5);
        // Pierwszy przycisk
        Button openButton = new Button("Open file");
        // Ustawiamy akcję
        openButton.setOnAction(new SingleFcButtonListener());
        // Tworzymy HBox, z odstępami między elementami = 5
        HBox szukanaHBox = new HBox(5);
        // Etykieta, tym razem przy tworzeniu obiektu przekazaliśmy argument -
        // tekst, który powinien się wyswietlać na etykiecie
        Label szukanaLabel = new Label("Szukaj: ");
        // Tworzymy TextField
        TextField szukanaTextField = new TextField();
        // Ustawiamy minimalną szerokość pola tekstowego
        szukanaTextField.setMinWidth(235);
        // Dodajemy Label i TextField do HBox-u
        szukanaHBox.getChildren().add(szukanaLabel);
        szukanaHBox.getChildren().add(szukanaTextField);
        // Dodajemy HBox do VBox-u
        siatkaPionowaVBox.getChildren().add(szukanaHBox);
        // Kolejna etykieta
        Label wynikLabel = new Label("Wynik:");
        // Dodajemy ją do VBox-u
        siatkaPionowaVBox.getChildren().add(wynikLabel);
        // Tworzymy TextArea do wypisywania wyniku
        TextArea wynikTextArea = new TextArea();
        // Wyłączamy możliwość edycji
        wynikTextArea.setEditable(false);
        // Włączamy zawijanie tekstu
        wynikTextArea.setWrapText(true);
        // Dodajemy do VBox-u
        siatkaPionowaVBox.getChildren().add(wynikTextArea);
        // Tworzymy HBox dla przycisków
        HBox buttonsHBox = new HBox(5);
        // Pierwszy przycisk
        Button szukajButton = new Button("Szukaj");
        // Ustawiamy akcję
        szukajButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


            }
        });

        // Teraz przycisk "Zakończ"
        Button zakonczButton = new Button("Zakończ");
        // Akcja dla przycisku "Zakończ"
        zakonczButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // Tak się powinno zakańczać działanie aplikacji w JavaFX
                Platform.exit();
            }
        });

        // Dodawanie przycisków do HBox-u, metoda addAll pozwala dodawać
        // na raz wiele elementów
        buttonsHBox.getChildren().addAll(szukajButton, zakonczButton, openButton);
        // Dodajemy buttonsHBox do siatkaPionowaVBox
        siatkaPionowaVBox.getChildren().add(buttonsHBox);
        // Tworzymy Scene
        Scene scene = new Scene(siatkaPionowaVBox, 300, 250);
        // Tutuł okna
        primaryStage.setTitle("Szukanie sekwencji");
        // Ustawianie sceny
        primaryStage.setScene(scene);
        // Pokaż
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    private class SingleFcButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            showSingleFileChooser();

        }

    }


    private DataFrame showSingleFileChooser() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            actionStatus.setText("File selected: " + selectedFile.getName());
            ArrayList<Class<? extends Value>> arr2 = new ArrayList<Class<? extends Value>>();
            arr2.add(Str.class);
            arr2.add(DateTime.class);
            arr2.add(com.projekt1.Double.class);
            arr2.add(Double.class);
            DataFrame d = new DataFrame(selectedFile.getName(), arr2, true);
            d.showDatabase();

        } else {
            System.out.println("Błąd");
        }
        return d;

    }
}
