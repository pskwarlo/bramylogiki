package org.example.gates;

import org.example.model.Gate;
import org.example.controllers.gate.NandGateController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NAndGate extends Gate {
    private NandGateController nandGateController;

    public NAndGate() {
        super(false,false,false, NAND_GATE);
        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(this.getClass().getResource("NandGate.fxml"));
            Parent root = loader.load();

            this.nandGateController = loader.<NandGateController>getController();
            AnchorPane.setTopAnchor(root,0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        } catch (IOException ioe) {
        }
    }

    public ImageView getNandGate() {
        return nandGateController.getNandGate();
    }

    public ImageView getOutputImage() {
        return nandGateController.getOutputConnector();
    }

    public ImageView getInputAImage() {
        return nandGateController.getInputA();
    }
    public ImageView getInputBImage() {
        return nandGateController.getInputB();
    }
}
