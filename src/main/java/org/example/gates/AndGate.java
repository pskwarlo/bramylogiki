package org.example.gates;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.example.controller.gates.AndGateController;

import java.io.IOException;
@Getter
public class AndGate extends Gate{
    private AndGateController andGateController;
    public AndGate() {
        super(false, false, false, GateType.AND);
    }
    private void initialize() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("andGate.fxml"));
            Parent root = fxmlLoader.load();
            this.andGateController = fxmlLoader.<AndGateController>getController();
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        }
        catch(IOException e){
            System.err.println("Can't load a file");
        }
    }
}
