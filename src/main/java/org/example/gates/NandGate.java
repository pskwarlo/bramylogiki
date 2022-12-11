package org.example.gates;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.example.controller.gates.NandGateController;

import java.io.IOException;
@Getter
public class NandGate extends Gate {
    private NandGateController nandGateController;

    public NandGate() {
        super(false, false, false, GateType.NAND);
        initialize();
    }
    private void initialize(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("NandGate.fxml"));
            Parent root = loader.load();
            this.nandGateController = loader.<NandGateController>getController();
            AnchorPane.setTopAnchor(root,0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        }
        catch(IOException e){
            System.err.println("Can't load a file");
        }
    }
}