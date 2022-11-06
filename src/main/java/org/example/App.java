package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tytul gry");
        stage.show();
        var panel1 = new Pane(label1);
        panel1.setStyle("-fx-background-color: yellow;");
        var label2 = new Label("Otwórz bramę");
        var label3 = new Label("Dostosuj bramę");
        var label4= new Label("Zamknij bramę");
        var scene = new Scene(new StackPane(label1, label2, label3, label4), 1280, 1024);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
