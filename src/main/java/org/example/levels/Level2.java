package org.example.levels;

import javafx.fxml.FXML;
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
public class Level2 implements LevelController {

    @FXML
    private ImageView btnPower;
    @FXML
    private Pane pnlCanvas;
    @FXML
    private AnchorPane btnVcc;
    @FXML
    private AnchorPane btnGround;
    @FXML
    private AnchorPane btnAndGate;
    @FXML
    private AnchorPane btnNotGate;
    @FXML
    private AnchorPane btnSave;
    @FXML
    private AnchorPane btnRestart;
    private LEDGate ledGate;
    private DragController dragController;

    private static final int CURRENT_LEVEL = 2;
    private static final int MIN_LEVEL_MOVES = 2;

    @FXML
    public void initialize() {
        dragController = new DragController(this);

        btnSave.setOnMouseClicked(event -> GameStateHelper.saveGame(CURRENT_LEVEL,
                (UserData) pnlCanvas.getScene().getUserData()));
        btnRestart.setOnMouseClicked(event -> LevelSwitcher.goToLevel(pnlCanvas, CURRENT_LEVEL));
        ledGate = GateInit.initLed(1000, 200, dragController, pnlCanvas);


        GateInit.initAnd(800, 200, dragController, pnlCanvas);
        GateInfo.andGateInfo(pnlCanvas);

        GateInitButton.initAndBtn(btnAndGate, pnlCanvas);
        GateInitButton.initNotBtn(btnNotGate, pnlCanvas);

        initBtnPower();
    }

    private void initBtnPower() {
        btnPower.setOnMouseClicked(event -> {
            dragController.getDecoder().run();
            if(ledGate.getInputANode() instanceof PowerGate) {
                LevelSwitcher.cheatWarning(pnlCanvas, CURRENT_LEVEL);
                return;
            }
            if (ledGate.getOutputValue()) {
                LevelSwitcher.onWin(pnlCanvas, dragController, CURRENT_LEVEL, MIN_LEVEL_MOVES);
            }
        });
    }

    public void onEscape(KeyEvent event) {
        if(event.getCode() == KeyCode.ESCAPE) {
            LevelSwitcher.onEscape(pnlCanvas, CURRENT_LEVEL);
        }
    }
}
