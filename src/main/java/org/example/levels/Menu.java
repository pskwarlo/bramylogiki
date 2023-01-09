package org.example.levels;

import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.example.model.GameSave;
import org.example.model.UserData;

public class Menu {
    @FXML
    private TextArea login;

    @SneakyThrows
    public void exit() {
        ((Stage) login.getScene().getWindow()).close();
    }

    @SneakyThrows
    public void loadGame() {
        try {
            GameSave gameSave = GameStateHelper.loadGame();
            UserData userData = new UserData(gameSave.getScore(), gameSave.getUsername());
            var tmp = getClass().getResource(String.format("level%d.fxml",
                    gameSave.getLevel()));
            if (tmp == null) {
                throw new FileNotFoundException();
            }
            Parent root = FXMLLoader.load(tmp);
            Stage stage = (Stage) login.getScene().getWindow();
            Scene scene = new Scene(root, 1280, 1024);
            scene.setUserData(userData);
            stage.setScene(scene);
        } catch (FileNotFoundException ex) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("OK",
                    ButtonBar.ButtonData.OK_DONE));
            dialog.setContentText("Nie znaleziono pliku zapisu lub jest on niepoprawny!");
            dialog.show();
        }
    }

    @SneakyThrows
    public void runInSandboxMode() {
        Parent root = FXMLLoader.load(getClass().getResource("sandbox.fxml"));
        Stage stage = (Stage) login.getScene().getWindow();
        //TODO: Zaimplementowac przyciski w Sandboxie
        Scene scene = new Scene(root, 1280, 1024);
        stage.setScene(scene);
    }

    @SneakyThrows
    public void showBestScores() {
        Image image = new Image(getClass().getResource("background.jpg").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Parent root = FXMLLoader.load(getClass().getResource("bestScore.fxml"));
        VBox vBox = new VBox(root);
        vBox.setBackground(new Background(backgroundImage));
        Stage stage = (Stage) login.getScene().getWindow();
        Scene scene = new Scene(vBox, 1280, 1024);
        stage.setScene(scene);
    }

    @SneakyThrows
    public void startGame() {
        if (login.getText().isEmpty()) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("OK",
                    ButtonBar.ButtonData.OK_DONE));
            dialog.setContentText("Podaj nazwę użytkownika!");
            dialog.showAndWait();
            return;
        }
        UserData userData = new UserData(0, login.getText().replace('\n', ' '));
        Parent root = FXMLLoader.load(getClass().getResource("level0.fxml"));
        Stage stage = (Stage) login.getScene().getWindow();
        Scene scene = new Scene(root, 1280, 1024);
        scene.setUserData(userData);
        stage.setScene(scene);

    }
}
