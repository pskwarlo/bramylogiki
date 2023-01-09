package org.example.levels;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import org.example.controllers.DragController;
import org.example.controllers.LevelController;
import org.example.gates.*;
import org.example.model.UserData;

@Getter
public class Level0 implements LevelController {
    private DragController dragController;

    @FXML
    private ImageView btnPower;

    @FXML
    private Pane pnlCanvas;

    private int CURRENT_LEVEL = 0;

    @FXML
    public void initialize(){
        dragController = new DragController(this);
        btnPower.setOnMouseClicked(event -> {
            dragController.getDecoder().run();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setContentText("Zaczynamy?");
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("Tak",
                    ButtonBar.ButtonData.OK_DONE));
            dialog.showAndWait()
                    .filter(response -> response.getButtonData() == ButtonBar.ButtonData.OK_DONE)
                    .ifPresent(response -> LevelSwitcher.goToLevel(pnlCanvas, CURRENT_LEVEL + 1));
    });
    }
}
