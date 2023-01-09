package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import org.example.model.UserData;


public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setHeight(1024);
        primaryStage.setWidth(1280);

        try {
            var tmp = this.getClass().getResource("levels/menu.fxml");
            System.out.println(tmp);
            Parent root = FXMLLoader.load(tmp);
            primaryStage.setTitle("Bramy Logiki");
            Scene scene = new Scene(root, 1280, 1024);
            scene.setUserData(new UserData(0, "myUser"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
