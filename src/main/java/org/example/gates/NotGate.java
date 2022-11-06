package org.example.gates;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.example.controller.gates.NotGateController;

import java.io.IOException;

@Getter
public class NotGate extends Gate {

    private NotGateController notGateController;

    public NotGate() {
        super(false, false, false, GateType.NOT);
        initialize();
    }
    private void initialize(){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("notGate.fxml"));
            Parent root = loader.load();
            this.notGateController = loader.getController();
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        } catch (IOException e) {
            System.err.println("Can't load a file");
        }
    }
}