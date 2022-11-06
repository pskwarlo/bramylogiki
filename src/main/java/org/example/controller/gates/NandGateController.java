package org.example.controller.gates;
import javafx.fxml.FXML;

import lombok.Getter;

import javax.swing.text.html.ImageView;

@Getter
public class NandGateController {
    @FXML
    private ImageView firstInput;
    @FXML
    private ImageView secondInput;
    @FXML
    private ImageView outputConnector;
    @FXML
    private ImageView nandGate;
}