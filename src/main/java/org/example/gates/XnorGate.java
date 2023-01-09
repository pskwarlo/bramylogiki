package org.example.gates;

import org.example.model.Gate;
import org.example.controllers.gate.XnorGateController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class XnorGate extends Gate {

    private XnorGateController xnorGateController;

    public XnorGate() {
        super(false,false,false, XNOR_GATE);

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(this.getClass().getResource("XnorGate.fxml"));

            Parent root = fxmlLoader.load();

            this.xnorGateController = fxmlLoader.<XnorGateController>getController();

            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public ImageView getXnorGate() {
        return this.xnorGateController.getXnorGate();
    }
    public ImageView getInputAImage() {
        return this.xnorGateController.getInputA();
    }
    public ImageView getInputBImage() {
        return this.xnorGateController.getInputB();
    }
    public ImageView getOutput() {
        return this.xnorGateController.getOutput();
    }
}
