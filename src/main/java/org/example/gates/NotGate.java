package org.example.gates;

import org.example.model.Gate;
import org.example.controllers.gate.NotGateController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NotGate extends Gate {


    private NotGateController notGateController;


    public NotGate() {
        super(false,false,false, NOT_GATE);

        try {

            FXMLLoader loader = new FXMLLoader();
            var tmp = this.getClass().getResource("NotGate.fxml");
            System.out.println(tmp);
            loader.setLocation(tmp);
            Parent root = loader.load();

            this.notGateController = loader.<NotGateController>getController();
            AnchorPane.setTopAnchor(root,0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);

        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    public ImageView getNotGate() {
        return notGateController.getNotGate();
    }

    public ImageView getOutputImage() {
        return notGateController.getOutputConnector();
    }

    public ImageView getInputA() {
        return notGateController.getInputA();
    }


}
