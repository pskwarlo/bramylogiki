package org.example.gates;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.example.controller.gates.AndGateController;
import org.example.controller.gates.NorGateController;

import java.io.IOException;
@Getter
public class NorGate extends Gate {
    private NorGateController norGateController;

    public NorGate() {
        super(false, false, false, GateType.NOR);
        initialize();
    }
    private void initialize() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("andGate.fxml"));
            Parent root = fxmlLoader.load();
            this.norGateController = fxmlLoader.<NorGateController>getController();
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        }
        catch(IOException e){
            System.err.println("Can't load a file");
        }
    }
}
