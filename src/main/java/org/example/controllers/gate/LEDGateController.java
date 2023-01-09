package org.example.controllers.gate;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class LEDGateController {
    @FXML
    private ImageView ledImage,inputConnector;

    public ImageView getLedImage() {
        return ledImage;
    }

    public ImageView getInputConnector() {
        return inputConnector;
    }
}
