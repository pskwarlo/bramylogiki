package org.example.controllers.gate;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class XorGateController {
    @FXML
    private ImageView inputA;
    @FXML
    private ImageView inputB;
    @FXML
    private ImageView outputConnector;
    @FXML
    private ImageView xOrGate;


    public ImageView getXorGate() {
        return xOrGate;
    }

    public ImageView getInputB() {
        return inputB;
    }

    public ImageView getInputA() {
        return inputA;
    }

    public ImageView getOutputConnector() {
        return outputConnector;
    }
}
