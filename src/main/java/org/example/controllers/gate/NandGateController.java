package org.example.controllers.gate;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class NandGateController {
    
    @FXML
    private ImageView inputA;
    @FXML
    private ImageView inputB;
    @FXML
    private ImageView outputConnector;
    @FXML
    private ImageView nandGate;
    

    public ImageView getOutputConnector() {
        return outputConnector;
    }

    public ImageView getInputA() {
        return inputA;
    }

    public ImageView getInputB() {
        return inputB;
    }

    public ImageView getNandGate() {
        return nandGate;
    }

}
