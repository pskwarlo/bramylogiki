package org.example.controllers.gate;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class PowerGateController {

    @FXML
    private ImageView powerSource;

    @FXML
    private ImageView outputConnector;

    @FXML
    public void initialize() {
    }

    public ImageView getPowerSourceImage() {
        return this.powerSource;
    }

    public ImageView getOutputConnector() {
        return this.outputConnector;
    }
}
