package org.example.gates;


import org.example.controllers.gate.OrGateController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import org.example.model.Gate;

public class OrGate extends Gate {

    private OrGateController orGateController;

    public OrGate() {
        super(false,false,false, OR_GATE);

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(this.getClass().getResource("OrGate.fxml"));

            Parent root = fxmlLoader.load();

            this.orGateController = fxmlLoader.<OrGateController>getController();

            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public ImageView getOrGate() {
        return this.orGateController.getOrGate();
    }
    public ImageView getInputAImage() {
        return this.orGateController.getInputA();
    }
    public ImageView getInputBImage() {
        return this.orGateController.getInputB();
    }
    public ImageView getOutputConnector() {
        return this.orGateController.getOutputConnector();
    }
}
