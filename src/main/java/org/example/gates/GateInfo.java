package org.example.gates;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.Window;

public class GateInfo {
    private static double pos = 10;
    public static Window notGateInfo(Pane pnlCanvas) {
        Window window = new Window("Bramka NOT");
        window.setLayoutX(pos);
        window.setLayoutY(pos);
        window.getRightIcons().add(new CloseIcon(window));
        window.getContentPane().getChildren().add(new ImageView("org/example/tables/NOT.png"));
        pnlCanvas.getChildren().add(window);
        return window;
    }
    public static Window andGateInfo(Pane pnlCanvas){
        Window window = new Window("Bramka AND");
        window.setLayoutX(pos);
        window.setLayoutY(pos);
        window.getRightIcons().add(new CloseIcon(window));
        window.getContentPane().getChildren().add(new ImageView("org/example/tables/AND.png"));
        pnlCanvas.getChildren().add(window);
        return window;
    }
    public static Window nandGateInfo(Pane pnlCanvas){
        Window window = new Window("Bramka NAND");
        window.setLayoutX(pos);
        window.setLayoutY(pos);
        window.getRightIcons().add(new CloseIcon(window));
        window.getContentPane().getChildren().add(new ImageView("org/example/tables/NAND.png"));
        pnlCanvas.getChildren().add(window);
        return window;
    }

    public static Window xorGateInfo(Pane pnlCanvas){
        Window window = new Window("Bramka XOR");
        window.setLayoutX(pos);
        window.setLayoutY(pos);
        window.getRightIcons().add(new CloseIcon(window));
        window.getContentPane().getChildren().add(new ImageView("org/example/tables/XOR.png"));
        pnlCanvas.getChildren().add(window);
        return window;
    }

    public static Window norGateInfo(Pane pnlCanvas){
        Window window = new Window("Bramka NOR");
        window.setLayoutX(pos);
        window.setLayoutY(pos);
        window.getRightIcons().add(new CloseIcon(window));
        window.getContentPane().getChildren().add(new ImageView("org/example/tables/NOR.png"));
        pnlCanvas.getChildren().add(window);
        return window;
    }
    public static Window xnorGateInfo(Pane pnlCanvas){
        Window window = new Window("Bramka XNOR");
        window.setLayoutX(pos);
        window.setLayoutY(pos);
        window.getRightIcons().add(new CloseIcon(window));
        window.getContentPane().getChildren().add(new ImageView("org/example/tables/XNOR.png"));
        pnlCanvas.getChildren().add(window);
        return window;
    }
    public static Window orGateInfo(Pane pnlCanvas){
        Window window = new Window("Bramka OR");
        window.setLayoutX(pos);
        window.setLayoutY(pos);
        window.getRightIcons().add(new CloseIcon(window));
        window.getContentPane().getChildren().add(new ImageView("org/example/tables/OR.png"));
        pnlCanvas.getChildren().add(window);
        return window;
    }
}
