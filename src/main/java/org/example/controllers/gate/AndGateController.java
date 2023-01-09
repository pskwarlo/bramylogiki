package org.example.controllers.gate;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;


public class AndGateController {

    @FXML
    private ImageView andGate;
    @FXML
    private ImageView outputConnector;
    @FXML
    private ImageView inputA;
    @FXML
    private ImageView inputB;


    public ImageView getAndGate() {
        return andGate;
    }

    public ImageView getOutputConnector() {
        return outputConnector;
    }

    public ImageView getInputA() {
        return inputA;
    }

    public ImageView getInputB() {
        return inputB;
    }



}
