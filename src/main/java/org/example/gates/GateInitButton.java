package org.example.gates;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.Window;
import org.example.controllers.DragController;

public class GateInitButton {
    public static void initAndBtn(Node node, Pane pnlCanvas){
        node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.MIDDLE) {
                GateInfo.andGateInfo(pnlCanvas);
            }
        });
    }
    public static void initNotBtn(Node node, Pane pnlCanvas){
        node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.MIDDLE) {
                GateInfo.notGateInfo(pnlCanvas);
            }
        });
    }
    public static void initXorBtn(Node node, Pane pnlCanvas){
        node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.MIDDLE) {
                GateInfo.xorGateInfo(pnlCanvas);
            }
        });
    }

    public static void initNorBtn(Node node, Pane pnlCanvas){
        node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.MIDDLE) {
                GateInfo.norGateInfo(pnlCanvas);
            }
        });
    }
    public static void initXnorBtn(Node node, Pane pnlCanvas){
        node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.MIDDLE) {
                GateInfo.xnorGateInfo(pnlCanvas);}
    });
    }

    public static void initNandBtn(Node node, Pane pnlCanvas){
        node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.MIDDLE) {
                GateInfo.nandGateInfo(pnlCanvas);
            }
        });
    }
    public static void initOrBtn(Node node, Pane pnlCanvas){
        node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.MIDDLE) {
                GateInfo.orGateInfo(pnlCanvas);
            }
        });
    }
}
