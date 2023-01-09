package org.example.gates;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import org.example.controllers.gate.XorGateController;
import org.example.model.Gate;


public class XorGate extends Gate {

    private XorGateController xorGateController;

    public XorGate() {
        super(false, false, false, XOR_GATE);

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(this.getClass().getResource("XorGate.fxml"));

            Parent root = fxmlLoader.load();

            this.xorGateController = fxmlLoader.<XorGateController>getController();

            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public ImageView getXorGate() {
        return this.xorGateController.getXorGate();
    }
    public ImageView getInputAImage() {
        return this.xorGateController.getInputA();
    }
    public ImageView getInputBImage() {
        return this.xorGateController.getInputB();
    }
    public ImageView getOutput() {
        return this.xorGateController.getOutputConnector();
    }
}
