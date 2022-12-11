package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.gates.AndGate;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Bramy Logiki");
        //FXMLLoader fxmlloaderAnd = new FXMLLoader(getClass().getResource("AndGate.fxml"));
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("NandGate.fxml"));
        Scene scene = new Scene(fxmlloader.load(), 1280, 1024);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
