package org.example.gates;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.example.controller.gates.XorGateController;

import java.io.IOException;
@Getter
public class XorGate extends Gate {

    private XorGateController xorGateController;

    public XorGate() {
        super(false, false, false, GateType.XOR);
        initialize();
    }
    private void initialize(){
            try{
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(this.getClass().getResource("XorGate.fxml"));

            Parent root = fxmlLoader.load();

            this.xorGateController = fxmlLoader.<XorGateController>getController();

            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        }
        catch(IOException e){
            System.err.println("Can't load a file");
        }
    }
}