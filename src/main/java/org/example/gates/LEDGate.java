package org.example.gates;

import org.example.model.Circuit;
import org.example.controllers.gate.LEDGateController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.io.IOException;


public class LEDGate extends Circuit {


    private Line inputLine;

    private LEDGateController controller;

    public LEDGate() {
        super(false);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("LEDGate.fxml"));
            Parent root = fxmlLoader.load();
            this.controller = fxmlLoader.<LEDGateController>getController();
            this.getChildren().add(root);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public void toggleInput() {
        setInputA(!getInputAValue());
    }


    public void setInputLine(Line inputLine) {
        this.inputLine = inputLine;
    }

    public Line getInputLine() {
        return inputLine;
    }


    public void setLed(final boolean input) {
        super.setInputA(input);
        if(input) {
            this.controller.getLedImage().setImage(new Image("org/example/icons/lamp_on.png"));
        } else {
            this.controller.getLedImage().setImage(new Image("org/example/icons/lampoff.png"));
        }
    }

    public ImageView getLEDImage() {
        return this.controller.getLedImage();
    }
    public ImageView getInputImage() {
        return this.controller.getInputConnector();
    }

    @Override
    public boolean getOutputValue() {
        return this.getInputAValue();
    }
}
