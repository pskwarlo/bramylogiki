package org.example.gates;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.example.controller.gates.XnorGateController;

import java.io.IOException;



@Getter
public class XnorGate extends Gate {

    private XnorGateController xnorGateController;

    public XnorGate() {
        super(false, false, false, GateType.XNOR);
        initialize();
    }
    private void initialize(){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(this.getClass().getResource("XnorGate.fxml"));

            Parent root = fxmlLoader.load();

            this.xnorGateController = fxmlLoader.<XnorGateController>getController();

            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        }
        catch(IOException e){
            System.err.println("Can't load a file");
        }
    }
}