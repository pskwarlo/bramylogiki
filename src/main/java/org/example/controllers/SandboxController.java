package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.example.levels.LevelSwitcher;


public class SandboxController implements LevelController{

    private DragController dragEventHandler;
    @FXML
    private AnchorPane btnAndGate;
    @FXML
    private AnchorPane btnOrGate;
    @FXML
    private AnchorPane btnNotGate;
    @FXML
    private AnchorPane btnNandGate;

    @FXML
    private ImageView btnPower;

    @FXML
    private AnchorPane btnNorGate;

    @FXML
    private AnchorPane btnXorGate;

    @FXML
    private AnchorPane btnVcc;

    @FXML
    private AnchorPane btnGround;

    @FXML
    private AnchorPane btnXnorGate;

    @FXML
    private AnchorPane btnLED;

    @FXML
    private Pane pnlCanvas;

    private AnchorPane btnRestart;

    @FXML
    public void initialize() {
        this.dragEventHandler = new DragController(this);
    }

    public AnchorPane getAndGateButton() {
        return this.btnAndGate;
    }
    public AnchorPane getOrGateButton() {
        return this.btnOrGate;
    }
    public AnchorPane getNotGateButton() {
        return this.btnNotGate;
    }
    public AnchorPane getNandGateButton() {
        return this.btnNandGate;
    }
    public AnchorPane getNorButtonGate() {
        return this.btnNorGate;
    }
    public AnchorPane getXorGateButton() {
        return this.btnXorGate;
    }
    public AnchorPane getXnorGateButton() {
        return this.btnXnorGate;
    }


    public AnchorPane getVccButton() {
        return this.btnVcc;
    }
    public AnchorPane getGroundButton() {
        return this.btnGround;
    }

    public ImageView getPowerButton() {
        return this.btnPower;
    }
    public AnchorPane getLEDButton() {
        return this.btnLED;
    }
    public Pane getCanvasPanel() {
        return this.pnlCanvas;
    }

    //btnRestart.setOnMouseClicked(event -> LevelSwitcher.goToSandbox(pnlCanvas));

    public void onEscape(KeyEvent event) {
        if(event.getCode() == KeyCode.ESCAPE) {
            LevelSwitcher.goToMenu(pnlCanvas);
        }
    }
}
