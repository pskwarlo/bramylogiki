package org.example.controller.gates;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import lombok.Getter;

@Getter
public class AndGateController {
    @FXML
    private ImageView firstInput;
    @FXML
    private ImageView secondInput;
    @FXML
    private ImageView outputConnector;
    @FXML
    private ImageView andGate;
}
