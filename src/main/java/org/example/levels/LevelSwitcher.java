package org.example.levels;

import java.io.File;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.example.controllers.DragController;
import org.example.model.UserData;

@UtilityClass
public class LevelSwitcher {
    private static final String PATH_TO_FXML = "src/main/resources/org/example/levels/";
    private static final Class CLAZZ = LevelSwitcher.class;

    @SneakyThrows
    public static void goToLevel(Pane pnlCanvas, int level) {
        UserData userData = (UserData) pnlCanvas.getScene().getUserData();
//        String filename = String.format("level%d.fxml", level);
//        Parent root = FXMLLoader.load(new File(PATH_TO_FXML + filename).toURI().toURL());
        Parent root = FXMLLoader.load(CLAZZ.getResource(String.format("level%d.fxml", level)));
        Stage stage = (Stage) pnlCanvas.getParent().getScene().getWindow();
        Scene scene = new Scene(root, 1280, 1024);
        scene.setUserData(userData);
        stage.setScene(scene);
    }

    @SneakyThrows
    public static void goToMenu(Pane pnlCanvas) {
//        Parent root = FXMLLoader.load(new File(PATH_TO_FXML + "menu.fxml").toURI().toURL());
        Parent root = FXMLLoader.load(CLAZZ.getResource("menu.fxml"));
        Stage stage = (Stage) pnlCanvas.getParent().getScene().getWindow();
        Scene scene = new Scene(root, 1280, 1024);
        stage.setScene(scene);
    }

    public static void onEscape(Pane pnlCanvas, int level) {
        UserData userData = (UserData) pnlCanvas.getScene().getUserData();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setContentText("Czy chcesz zapisać stan gry przed wyjściem do menu?");
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("Tak",
                ButtonBar.ButtonData.YES));
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("Nie", ButtonBar.ButtonData.NO));
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("Anuluj",
                ButtonBar.ButtonData.CANCEL_CLOSE));

        dialog.showAndWait().ifPresent(x -> {
            if (x.getButtonData() == ButtonBar.ButtonData.YES) {
                GameStateHelper.saveGame(level, userData);
                LevelSwitcher.goToMenu(pnlCanvas);
            } else if (x.getButtonData() == ButtonBar.ButtonData.NO) {
                LevelSwitcher.goToMenu(pnlCanvas);
            }
        });
    }

    public static void onWin(Pane pnlCanvas, DragController dragController, int level, int minLevelMoves) {
        int moves = dragController.getMoves();
        int pointsForLevel = scoreCount(pnlCanvas, level, moves, minLevelMoves);
        if (pointsForLevel >= 0) {
            UserData userData = (UserData) pnlCanvas.getScene().getUserData();
            userData.setScore(userData.getScore() + pointsForLevel);
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("Następny poziom",
                    ButtonBar.ButtonData.OK_DONE));
            dialog.setHeaderText(String.format("Brawo %s!", userData.getUsername()));
            dialog.setContentText(String.format("Twój wynik za ten poziom to: %d\nW całości Twój " +
                    "wynik wynosi: %d", pointsForLevel, userData.getScore()));
            dialog.showAndWait()
                    .filter(response -> response.getButtonData() == ButtonBar.ButtonData.OK_DONE)
                    .ifPresent(response -> goToLevel(pnlCanvas, level + 1));
        }
    }

    public static int scoreCount(Pane pnlCanvas, int level, int moves, int minLevelMoves) {
        if (moves < minLevelMoves) {
            cheatWarning(pnlCanvas, level);
            return -1;
        }
        else if (moves == minLevelMoves) {
            System.out.println("Maksymalna ilosc punktow");
            return 100;
        } else if (moves <= minLevelMoves + 5) {
            System.out.println("Srednia ilosc punktow");
            return 50;
        } else if (moves > minLevelMoves + 5 && moves <= minLevelMoves + 10) {
            System.out.println("Niska ilosc punktow");
            return 20;
        } else if (moves > minLevelMoves + 10) {
            System.out.println("Za duzo ruchow, brak punktow");
            return 0;
        }
        return -1;
    }

    public static void onLastLevelWin(Pane pnlCanvas, int level, DragController dragController, int minLevelMoves) {
        int moves = dragController.getMoves();
        int pointsForLevel = scoreCount(pnlCanvas, level, moves, minLevelMoves);
        if(pointsForLevel >= 0) {
            UserData userData = (UserData) pnlCanvas.getScene().getUserData();
            userData.setScore(userData.getScore() + pointsForLevel);
            GameStateHelper.addToLeaderboard(userData);
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("Wróć do menu",
                    ButtonBar.ButtonData.OK_DONE));
            dialog.setHeaderText(String.format("Brawo %s!", userData.getUsername()));
            dialog.setContentText(String.format("Twój wynik za ten poziom to: %d\nW całości Twój " +
                    "wynik wynosi %d", pointsForLevel, userData.getScore()));
            dialog.showAndWait()
                    .filter(response -> response.getButtonData() == ButtonBar.ButtonData.OK_DONE)
                    .ifPresent(response -> goToMenu(pnlCanvas));
        }
    }

    public static void cheatWarning(Pane pnlCanvas, int level) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("Już nie będę oszukiwać",
                ButtonBar.ButtonData.OK_DONE));
        dialog.setHeaderText("Nie oszukuj - musisz wykorzystać wszystkie bramki które są na planszy od początku!");
        dialog.showAndWait()
                .filter(response -> response.getButtonData() == ButtonBar.ButtonData.OK_DONE)
                .ifPresent(response -> LevelSwitcher.goToLevel(pnlCanvas, level));
    }
}
