package org.example.levels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.example.model.Scoreboard;

public class BestScore {
    @FXML
    private ListView<String> namesBoard;

    @FXML
    private ListView<Integer> pointsBoard;

    @SneakyThrows
    public void goToMenu() {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Stage stage = (Stage) namesBoard.getParent().getScene().getWindow();
        Scene scene = new Scene(root, 1280, 1024);
        stage.setScene(scene);
    }

    @FXML
    public void initialize() {
        Scoreboard scoreboard = GameStateHelper.takeLeaderboard();
        scoreboard.sortScores();
        for (var item : scoreboard.getScores()) {
            namesBoard.getItems().add(item.getUsername());
            pointsBoard.getItems().add(item.getScore());
        }
    }
}
