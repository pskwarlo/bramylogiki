package org.example.gates;

import org.example.model.Gate;
import org.example.controllers.gate.NorGateController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NorGate extends Gate {
    private NorGateController norGateController;


    public NorGate() {
        super(false,false,false, NOR_GATE);

        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(this.getClass().getResource("NorGate.fxml"));
            Parent root = loader.load();

            this.norGateController = loader.<NorGateController>getController();
            AnchorPane.setTopAnchor(root,0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        } catch (IOException ioe) {
        }
    }

    public ImageView getNorGate() {
        return norGateController.getNorGate();
    }

    public ImageView getOutputImage() {
        return norGateController.getOutputConnector();
    }

    public ImageView getInputAImage() {
        return norGateController.getInputA();
    }
    public ImageView getInputBImage() {
        return norGateController.getInputB();
    }
}
