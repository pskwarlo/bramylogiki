package org.example.gates;

import org.example.model.Gate;
import org.example.controllers.gate.AndGateController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AndGate extends Gate {


    private AndGateController andGateController;


    public AndGate() {
        super(false,false,false, AND_GATE);

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("AndGate.fxml"));
            Parent root = loader.load();

            this.andGateController = loader.getController();
            AnchorPane.setTopAnchor(root,0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);

        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    public ImageView getAndGate() {
        return andGateController.getAndGate();
    }

    public ImageView getOutputImage() {
        return andGateController.getOutputConnector();
    }

    public ImageView getInputAImage() {
        return andGateController.getInputA();
    }
    public ImageView getInputBImage() {
        return andGateController.getInputB();
    }

    @Override
    public String toString() {
        return andGateController.toString();
    }
}
