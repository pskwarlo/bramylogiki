package org.example.gates;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.Window;
import org.example.controllers.DragController;

public class GateInit {
    public static LEDGate initLed(double x, double y, DragController dragController, Pane pnlCanvas){
        LEDGate led = new LEDGate();
        led.setLayoutX(x);
        led.setLayoutY(y);
        dragController.addListenersToNode(led);
        pnlCanvas.getChildren().add(led);
        return led;
    }
    public static void initPower(boolean isGround, double x, double y,
                                 DragController dragController,
                                 Pane pnlCanvas) {
        PowerGate gate = new PowerGate(isGround);
        gate.setLayoutX(x);
        gate.setLayoutY(y);
        dragController.addListenersToNode(gate);
        dragController.getDecoder().addPowerSource(gate);
        pnlCanvas.getChildren().add(gate);
    }
    public static void initNot(double x, double y, DragController dragController, Pane pnlCanvas ){
        NotGate notGate = new NotGate();
        notGate.setLayoutX(x);
        notGate.setLayoutY(y);
        pnlCanvas.getChildren().add(notGate);
        notGate.getNotGate().setOnDragDetected(e -> dragController.componentDragDetected(e));
        notGate.getOutputImage().setOnDragDetected(e -> dragController.outputDragDetected(e));
        notGate.getInputA().setOnDragOver(e -> dragController.inputDragOver(e));
        notGate.getInputA().setOnDragEntered(e -> dragController.inputDragEntered(e));
        notGate.getInputA().setOnDragExited(e -> dragController.inputDragExited(e));
    }

    public static void initAnd(double x, double y, DragController dragController, Pane pnlCanvas){
        AndGate andGate = new AndGate();
        andGate.setLayoutX(x);
        andGate.setLayoutY(y);
        dragController.addListenersToNode(andGate);
        pnlCanvas.getChildren().add(andGate);
        andGate.setOnMouseClicked(e -> {});
    }
    public static void initXor(double x, double y, DragController dragController, Pane pnlCanvas) {
        XorGate xorGate = new XorGate();
        xorGate.setLayoutX(x);
        xorGate.setLayoutY(y);
        dragController.addListenersToNode(xorGate);
        pnlCanvas.getChildren().add(xorGate);
    }
    public static void initNand(double x, double y, DragController dragController, Pane pnlCanvas){
        NAndGate nandGate = new NAndGate();
        nandGate.setLayoutX(x);
        nandGate.setLayoutY(y);
        dragController.addListenersToNode(nandGate);
        pnlCanvas.getChildren().add(nandGate);
        nandGate.setOnMouseClicked(e -> {});
    }
    public static void initNor(double x, double y, DragController dragController, Pane pnlCanvas){
        NorGate norGate = new NorGate();
        norGate.setLayoutX(x);
        norGate.setLayoutY(y);
        dragController.addListenersToNode(norGate);
        pnlCanvas.getChildren().add(norGate);
        norGate.setOnMouseClicked(e -> {});
    }
    public static void initOr(double x, double y, DragController dragController, Pane pnlCanvas){
        OrGate orGate = new OrGate();
        orGate.setLayoutX(x);
        orGate.setLayoutY(y);
        dragController.addListenersToNode(orGate);
        pnlCanvas.getChildren().add(orGate);
        orGate.setOnMouseClicked(e -> {});
    }
    public static void initXnor(double x, double y, DragController dragController, Pane pnlCanvas){
        XnorGate xnorGate = new XnorGate();
        xnorGate.setLayoutX(x);
        xnorGate.setLayoutY(y);
        dragController.addListenersToNode(xnorGate);
        pnlCanvas.getChildren().add(xnorGate);
        xnorGate.setOnMouseClicked(e -> {});
    }

}
