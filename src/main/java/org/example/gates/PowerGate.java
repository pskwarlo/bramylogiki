package org.example.gates;

import org.example.model.PowerSource;
import org.example.controllers.gate.PowerGateController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class PowerGate extends PowerSource {

    private PowerGateController controller;

    public PowerGate(boolean isGround) {
        super(true);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("PowerGate.fxml"));
            Parent root = fxmlLoader.load();
            this.controller = fxmlLoader.<PowerGateController>getController();
            this.getChildren().add(root);
            if(isGround){
                initGround();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void initGround() {
        this.setOutput(false);
        this.controller.getPowerSourceImage().setImage(new Image("/org/example/icons/panel/grand" +
                ".png"));

    }


    public ImageView getPowerSourceImage() {
        return this.controller.getPowerSourceImage();
    }

    public ImageView getOutputConnector() {
        return this.controller.getOutputConnector();
    }
}
