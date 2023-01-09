package org.example.controllers;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import lombok.Getter;
import org.example.gates.*;
import org.example.levels.*;
import org.example.model.Circuit;
import org.example.model.Gate;
import org.example.model.PowerSource;

public class DragController implements Config {

    private static final String INPUT_A = "inputA";
    private static final String INPUT_B = "inputB";
    private static final String PUT_A = "A";
    private static final String PUT_B = "B";

    private SandboxController sandboxController;

    private Level1 level1Controller;

    private Level2 level2Controller;

    private Level3 level3Controller;

    private Level4 level4Controller;

    private Level5 level5Controller;

    private Level6 level6Controller;

    private Level7 level7Controller;

    private Level8 level8Controller;

    private Level9 level9Controller;

    private Level10 level10Controller;

    private Node currentNode;

    private AnchorPane anchorPane;

    private final boolean componentDropped;

    private boolean newComponent;

    private Pane pane;

    private final GateProgrammer decoder;

    @Getter
    private int moves = 0;

    @Getter
    private int connections = 0;


    public DragController(final LevelController levelController) {
        this.currentNode = null;
        this.componentDropped = false;
        this.newComponent = false;
        this.decoder = new GateProgrammer(this);

        if (levelController instanceof SandboxController) {
            this.sandboxController = (SandboxController) levelController;
            this.pane = sandboxController.getCanvasPanel();
        } else if (levelController instanceof Level1) {
            this.level1Controller = (Level1) levelController;
            this.pane = level1Controller.getPnlCanvas();
        } else if (levelController instanceof Level2) {
            this.level2Controller = (Level2) levelController;
            this.pane = level2Controller.getPnlCanvas();
        } else if (levelController instanceof Level3) {
            this.level3Controller = (Level3) levelController;
            this.pane = level3Controller.getPnlCanvas();
        } else if (levelController instanceof Level4) {
            this.level4Controller = (Level4) levelController;
            this.pane = level4Controller.getPnlCanvas();
        } else if (levelController instanceof Level5) {
            this.level5Controller = (Level5) levelController;
            this.pane = level5Controller.getPnlCanvas();
        } else if (levelController instanceof Level6) {
            this.level6Controller = (Level6) levelController;
            this.pane = level6Controller.getPnlCanvas();
        } else if (levelController instanceof Level7) {
            this.level7Controller = (Level7) levelController;
            this.pane = level7Controller.getPnlCanvas();
        } else if (levelController instanceof Level8){
            this.level8Controller = (Level8) levelController;
            this.pane = level8Controller.getPnlCanvas();
        } else if (levelController instanceof Level9){
            this.level9Controller = (Level9) levelController;
            this.pane = level9Controller.getPnlCanvas();
        } else if (levelController instanceof Level10){
            this.level10Controller = (Level10) levelController;
            this.pane = level10Controller.getPnlCanvas();
        }

        initListeners();
    }


    private void initListeners() {
        if (sandboxController != null) {
            sandboxController.getAndGateButton().setOnDragDetected(event -> handelDrag(event, TYPE_AND_GATE));
            sandboxController.getNandGateButton().setOnDragDetected(event -> handelDrag(event, TYPE_NAND_GATE));
            sandboxController.getOrGateButton().setOnDragDetected(event -> handelDrag(event, TYPE_OR_GATE));
            sandboxController.getNorButtonGate().setOnDragDetected(event -> handelDrag(event, TYPE_NOR_GATE));
            sandboxController.getNotGateButton().setOnDragDetected(event -> handelDrag(event, TYPE_NOT_GATE));
            sandboxController.getXorGateButton().setOnDragDetected(event -> handelDrag(event, TYPE_XOR_GATE));
            sandboxController.getXnorGateButton().setOnDragDetected(event -> handelDrag(event, TYPE_XNOR_GATE));

            sandboxController.getLEDButton().setOnDragDetected(event -> handelDrag(event, TYPE_LED));
            sandboxController.getVccButton().setOnDragDetected(event -> handelDrag(event, TYPE_VCC));
            sandboxController.getGroundButton().setOnDragDetected(event -> handelDrag(event, TYPE_GROUND));

            sandboxController.getPowerButton().setOnMouseClicked(this::run);
            sandboxController.getCanvasPanel().setOnMouseDragEntered(this::drawDragging);
            sandboxController.getCanvasPanel().setOnMouseDragExited(this::canvasDragExited);
            sandboxController.getCanvasPanel().setOnMouseDragOver(this::DragPosition);
        } else if (level1Controller != null) {
            level1Controller.getBtnVcc().setOnDragDetected(event -> handelDrag(event, TYPE_VCC));
            level1Controller.getBtnGround().setOnDragDetected(event -> handelDrag(event, TYPE_GROUND));
            level1Controller.getBtnPower().setOnMouseClicked(this::run);

            level1Controller.getPnlCanvas().setOnMouseDragEntered(this::drawDragging);
            level1Controller.getPnlCanvas().setOnMouseDragExited(this::canvasDragExited);
            level1Controller.getPnlCanvas().setOnMouseDragOver(this::DragPosition);
        } else if (level2Controller != null) {
            level2Controller.getBtnNotGate().setOnDragDetected(event -> handelDrag(event, TYPE_NOT_GATE));
            level2Controller.getBtnAndGate().setOnDragDetected(event -> handelDrag(event, TYPE_AND_GATE));

            level2Controller.getBtnVcc().setOnDragDetected(event -> handelDrag(event, TYPE_VCC));
            level2Controller.getBtnGround().setOnDragDetected(event -> handelDrag(event,
                    TYPE_GROUND));
            level2Controller.getBtnPower().setOnMouseClicked(this::run);

            level2Controller.getPnlCanvas().setOnMouseDragEntered(this::drawDragging);
            level2Controller.getPnlCanvas().setOnMouseDragExited(this::canvasDragExited);
            level2Controller.getPnlCanvas().setOnMouseDragOver(this::DragPosition);
        } else if (level3Controller != null) {
            level3Controller.getBtnAndGate().setOnDragDetected(event -> handelDrag(event, TYPE_AND_GATE));
            level3Controller.getBtnNotGate().setOnDragDetected(event -> handelDrag(event, TYPE_NOT_GATE));
            level3Controller.getBtnNandGate().setOnDragDetected(event -> handelDrag(event, TYPE_NAND_GATE));

            level3Controller.getBtnVcc().setOnDragDetected(event -> handelDrag(event, TYPE_VCC));
            level3Controller.getBtnGround().setOnDragDetected(event -> handelDrag(event,
                    TYPE_GROUND));

            level3Controller.getPnlCanvas().setOnMouseDragEntered(this::drawDragging);
            level3Controller.getPnlCanvas().setOnMouseDragExited(this::canvasDragExited);
            level3Controller.getPnlCanvas().setOnMouseDragOver(this::DragPosition);
        } else if (level4Controller != null) {
            level4Controller.getBtnAndGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_AND_GATE));
            level4Controller.getBtnNotGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NOT_GATE));
            level4Controller.getBtnNandGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NAND_GATE));

            level4Controller.getBtnXorGate().setOnDragDetected(event -> handelDrag(event, TYPE_XOR_GATE));

            level4Controller.getBtnVcc().setOnDragDetected(event -> handelDrag(event, TYPE_VCC));
            level4Controller.getBtnGround().setOnDragDetected(event -> handelDrag(event,
                    TYPE_GROUND));

            level4Controller.getPnlCanvas().setOnMouseDragEntered(this::drawDragging);
            level4Controller.getPnlCanvas().setOnMouseDragExited(this::canvasDragExited);
            level4Controller.getPnlCanvas().setOnMouseDragOver(this::DragPosition);
        } else if (level5Controller != null) {
            level5Controller.getBtnAndGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_AND_GATE));
            level5Controller.getBtnNotGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NOT_GATE));
            level5Controller.getBtnNandGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NAND_GATE));

            level5Controller.getBtnXorGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_XOR_GATE));

            level5Controller.getBtnVcc().setOnDragDetected(event -> handelDrag(event, TYPE_VCC));
            level5Controller.getBtnGround().setOnDragDetected(event -> handelDrag(event,
                    TYPE_GROUND));
            level5Controller.getBtnXnorGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_XNOR_GATE));

            level5Controller.getPnlCanvas().setOnMouseDragEntered(this::drawDragging);
            level5Controller.getPnlCanvas().setOnMouseDragExited(this::canvasDragExited);
            level5Controller.getPnlCanvas().setOnMouseDragOver(this::DragPosition);
        } else if (level6Controller != null) {
            level6Controller.getBtnAndGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_AND_GATE));
            level6Controller.getBtnNotGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NOT_GATE));
            level6Controller.getBtnNandGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NAND_GATE));
            level6Controller.getBtnXorGate().setOnDragDetected(event -> handelDrag(event, TYPE_XOR_GATE));
            level6Controller.getBtnOrGate().setOnDragDetected(event -> handelDrag(event, TYPE_OR_GATE));
            level6Controller.getBtnNorGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NOR_GATE));
            level6Controller.getBtnXnorGate().setOnDragDetected(event -> handelDrag(event, TYPE_XNOR_GATE));

            level6Controller.getBtnVcc().setOnDragDetected(event -> handelDrag(event, TYPE_VCC));
            level6Controller.getBtnGround().setOnDragDetected(event -> handelDrag(event,
                    TYPE_GROUND));

            level6Controller.getPnlCanvas().setOnMouseDragEntered(this::drawDragging);
            level6Controller.getPnlCanvas().setOnMouseDragExited(this::canvasDragExited);
            level6Controller.getPnlCanvas().setOnMouseDragOver(this::DragPosition);
        } else if (level7Controller != null) {
            level7Controller.getBtnAndGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_AND_GATE));
            level7Controller.getBtnNotGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NOT_GATE));
            level7Controller.getBtnNandGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NAND_GATE));
            level7Controller.getBtnXorGate().setOnDragDetected(event -> handelDrag(event, TYPE_XOR_GATE));
            level7Controller.getBtnOrGate().setOnDragDetected(event -> handelDrag(event, TYPE_OR_GATE));
            level7Controller.getBtnNorGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NOR_GATE));
            level7Controller.getBtnXnorGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_XNOR_GATE));
            level7Controller.getBtnVcc().setOnDragDetected(event -> handelDrag(event, TYPE_VCC));
            level7Controller.getBtnGround().setOnDragDetected(event -> handelDrag(event,
                    TYPE_GROUND));
            level7Controller.getPnlCanvas().setOnMouseDragEntered(this::drawDragging);
            level7Controller.getPnlCanvas().setOnMouseDragExited(this::canvasDragExited);
            level7Controller.getPnlCanvas().setOnMouseDragOver(this::DragPosition);
        } else if (level8Controller != null){
            level8Controller.getBtnAndGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_AND_GATE));
            level8Controller.getBtnNotGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NOT_GATE));
            level8Controller.getBtnNandGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NAND_GATE));
            level8Controller.getBtnXorGate().setOnDragDetected(event -> handelDrag(event, TYPE_XOR_GATE));
            level8Controller.getBtnOrGate().setOnDragDetected(event -> handelDrag(event, TYPE_OR_GATE));
            level8Controller.getBtnNorGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NOR_GATE));
            level8Controller.getBtnXnorGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_XNOR_GATE));
            level8Controller.getBtnVcc().setOnDragDetected(event -> handelDrag(event, TYPE_VCC));
            level8Controller.getBtnGround().setOnDragDetected(event -> handelDrag(event,
                    TYPE_GROUND));
            level8Controller.getPnlCanvas().setOnMouseDragEntered(this::drawDragging);
            level8Controller.getPnlCanvas().setOnMouseDragExited(this::canvasDragExited);
            level8Controller.getPnlCanvas().setOnMouseDragOver(this::DragPosition);
        } else if (level9Controller != null){
            level9Controller.getBtnAndGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_AND_GATE));
            level9Controller.getBtnNotGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NOT_GATE));
            level9Controller.getBtnNandGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NAND_GATE));
            level9Controller.getBtnXorGate().setOnDragDetected(event -> handelDrag(event, TYPE_XOR_GATE));
            level9Controller.getBtnOrGate().setOnDragDetected(event -> handelDrag(event, TYPE_OR_GATE));
            level9Controller.getBtnNorGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NOR_GATE));
            level9Controller.getBtnXnorGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_XNOR_GATE));
            level9Controller.getBtnVcc().setOnDragDetected(event -> handelDrag(event, TYPE_VCC));
            level9Controller.getBtnGround().setOnDragDetected(event -> handelDrag(event,
                    TYPE_GROUND));
            level9Controller.getPnlCanvas().setOnMouseDragEntered(this::drawDragging);
            level9Controller.getPnlCanvas().setOnMouseDragExited(this::canvasDragExited);
            level9Controller.getPnlCanvas().setOnMouseDragOver(this::DragPosition);
        } else if (level10Controller != null){
            level10Controller.getBtnAndGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_AND_GATE));
            level10Controller.getBtnNotGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NOT_GATE));
            level10Controller.getBtnNandGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NAND_GATE));
            level10Controller.getBtnXorGate().setOnDragDetected(event -> handelDrag(event, TYPE_XOR_GATE));
            level10Controller.getBtnOrGate().setOnDragDetected(event -> handelDrag(event, TYPE_OR_GATE));
            level10Controller.getBtnNorGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_NOR_GATE));
            level10Controller.getBtnXnorGate().setOnDragDetected(event -> handelDrag(event,
                    TYPE_XNOR_GATE));
            level10Controller.getBtnVcc().setOnDragDetected(event -> handelDrag(event, TYPE_VCC));
            level10Controller.getBtnGround().setOnDragDetected(event -> handelDrag(event,
                    TYPE_GROUND));
            level10Controller.getPnlCanvas().setOnMouseDragEntered(this::drawDragging);
            level10Controller.getPnlCanvas().setOnMouseDragExited(this::canvasDragExited);
            level10Controller.getPnlCanvas().setOnMouseDragOver(this::DragPosition);
        }
    }


    private Parent getNode(Object object) {
        return ((Node) object).getParent().getParent();
    }

    public Node createGate(String componentType) {

        return switch (componentType) {
            case TYPE_AND_GATE -> new AndGate();
            case TYPE_NAND_GATE -> new NAndGate();
            case TYPE_OR_GATE -> new OrGate();
            case TYPE_NOR_GATE -> new NorGate();
            case TYPE_NOT_GATE -> new NotGate();
            case TYPE_XOR_GATE -> new XorGate();
            case TYPE_XNOR_GATE -> new XnorGate();
            case TYPE_LED -> new LEDGate();
            case TYPE_GROUND -> new PowerGate(true);
            case TYPE_VCC -> new PowerGate(false);
            default -> null;
        };
    }

    public GateProgrammer getDecoder() {
        return decoder;
    }

    public void handelDrag(MouseEvent mouseEvent, String componentType) {
        ((Node) mouseEvent.getSource()).startFullDrag();
        this.currentNode = createGate(componentType);
        moves++;
        this.newComponent = false;
    }


    public void drawDragging(MouseDragEvent dragEvent) {
        try {
            if (!this.pane.getChildren().contains(currentNode))
                this.pane.getChildren().add(currentNode);
            if (this.currentNode instanceof PowerGate)
                this.decoder.addPowerSource((PowerGate) this.currentNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void DragPosition(MouseEvent mouseEvent) {
        this.setPosition(mouseEvent, currentNode);
    }


    private void run(MouseEvent mouseEvent) {
        this.decoder.run();
    }


    public void canvasDragExited(MouseDragEvent dragEvent) {
        if (!newComponent)
            addListenersToNode((AnchorPane) currentNode);
        this.newComponent = false;
        this.currentNode = null;
    }


    public void componentDragDetected(MouseEvent mouseEvent) {
        Object object = mouseEvent.getSource();
        if (object instanceof Node) {
            Node component = ((Node) object);
            getNode(component).startFullDrag();
            this.currentNode = component.getParent().getParent();
            this.newComponent = true;
        }
    }


    public void outputDragDetected(MouseEvent mouseEvent) {
        Node component = ((Node) mouseEvent.getSource());
        Dragboard dragboard = component.getParent().getParent().startDragAndDrop(TransferMode.MOVE);
        ClipboardContent clipboardContent = new ClipboardContent();

        double x = component.getLayoutX() + component.getParent().getParent().getLayoutX() + component.getLayoutBounds().getWidth() / 2;
        double y = component.getLayoutY() + component.getParent().getParent().getLayoutY() + component.getLayoutBounds().getHeight() / 2;

        this.currentNode = createLine(x, y, x, y);
        this.anchorPane = (AnchorPane) getNode(mouseEvent.getSource());

        mapLineTo(getNode(mouseEvent.getSource()), (Line) this.currentNode, ((Node) mouseEvent.getSource()).getId());
        connections++;

        Object object = mouseEvent.getSource();
        if (object instanceof ImageView)
            ((ImageView) object).setImage(new Image("org/example/icons/connector-positive.png"));

        clipboardContent.putString("String");
        dragboard.setContent(clipboardContent);
        setDragView(dragboard);
    }


    public void inputDragEntered(DragEvent dragEvent) {
        if (this.currentNode instanceof Line) {

            setLinePosition((Node) dragEvent.getSource(), (Line) this.currentNode);
            if (!this.pane.getChildren().contains(this.currentNode)) {
                this.pane.getChildren().add(this.currentNode);
            }
        }
    }


    public void inputDragOver(DragEvent dragEvent) {
        if (this.currentNode instanceof Line) {
            mapLineTo(getNode(dragEvent.getSource()), (Line) this.currentNode, ((Node) dragEvent.getSource()).getId());
            mapNodeTo(anchorPane, getNode(dragEvent.getSource()), ((Node) dragEvent.getSource()).getId());

            Object object = dragEvent.getSource();
            if (object instanceof ImageView) {
                ((ImageView) object).setImage(new Image("org/example/icons/connector-positive.png"));
            }
            ((Line) this.currentNode).setStroke(Color.web("#1e1e1e"));


            setLinePosition((Node) dragEvent.getSource(), (Line) this.currentNode);
            if (!this.pane.getChildren().contains(this.currentNode))
                this.pane.getChildren().add(this.currentNode);
            dragEvent.acceptTransferModes(TransferMode.MOVE);
        }
    }


    public void inputDragExited(DragEvent dragEvent) {
    }


    private void setPosition(final MouseEvent mouseEvent, final Node node) {
        if (node instanceof AnchorPane) {
            AnchorPane anchorPane = (AnchorPane) node;
            double layoutX = mouseEvent.getX() - anchorPane.getWidth() / 2;
            double layoutY = mouseEvent.getY() - anchorPane.getHeight() / 2;
            anchorPane.setLayoutX(layoutX);
            anchorPane.setLayoutY(layoutY);
            if (node instanceof LEDGate) {
                LEDGate ledComponent = (LEDGate) node;
                adjustLine(ledComponent.getInputLine(), layoutX, layoutY, ledComponent.getInputImage(), true);
            } else if (node instanceof AndGate) {
                AndGate andGate = (AndGate) node;
                adjustLine(andGate.getOutputLine(), layoutX, layoutY, andGate.getOutputImage(), false);
                adjustLine(andGate.getInputALine(), layoutX, layoutY, andGate.getInputAImage(), true);
                adjustLine(andGate.getInputBLine(), layoutX, layoutY, andGate.getInputBImage(), true);
            } else if (node instanceof OrGate) {
                OrGate orGate = (OrGate) node;
                adjustLine(orGate.getOutputLine(), layoutX, layoutY, orGate.getOutputConnector(), false);
                adjustLine(orGate.getInputALine(), layoutX, layoutY, orGate.getInputAImage(), true);
                adjustLine(orGate.getInputBLine(), layoutX, layoutY, orGate.getInputBImage(), true);
            } else if (node instanceof NAndGate) {
                NAndGate nandGate = (NAndGate) node;
                adjustLine(nandGate.getOutputLine(), layoutX, layoutY, nandGate.getOutputImage(), false);
                adjustLine(nandGate.getInputALine(), layoutX, layoutY, nandGate.getInputAImage(), true);
                adjustLine(nandGate.getInputBLine(), layoutX, layoutY, nandGate.getInputBImage(), true);
            } else if (node instanceof PowerGate) {
                PowerGate powerSourceComponent = (PowerGate) node;
                adjustLine(powerSourceComponent.getOutputLine(), layoutX, layoutY, powerSourceComponent.getOutputConnector(), false);
            } else if (node instanceof NorGate) {
                NorGate norGate = (NorGate) node;
                adjustLine(norGate.getOutputLine(), layoutX, layoutY, norGate.getOutputImage(), false);
                adjustLine(norGate.getInputALine(), layoutX, layoutY, norGate.getInputAImage(), true);
                adjustLine(norGate.getInputBLine(), layoutX, layoutY, norGate.getInputBImage(), true);
            } else if (node instanceof NotGate) {
                NotGate notGate = (NotGate) node;
                adjustLine(notGate.getOutputLine(), layoutX, layoutY, notGate.getOutputImage(), false);
                adjustLine(notGate.getInputALine(), layoutX, layoutY, notGate.getInputA(), true);
            } else if (node instanceof XorGate) {
                XorGate xorGate = (XorGate) node;
                adjustLine(xorGate.getOutputLine(), layoutX, layoutY, xorGate.getOutput(), false);
                adjustLine(xorGate.getInputALine(), layoutX, layoutY, xorGate.getInputAImage(), true);
                adjustLine(xorGate.getInputBLine(), layoutX, layoutY, xorGate.getInputBImage(), true);
            } else if (node instanceof XnorGate) {
                XnorGate xnorGate = (XnorGate) node;
                adjustLine(xnorGate.getOutputLine(), layoutX, layoutY, xnorGate.getOutput(), false);
                adjustLine(xnorGate.getInputALine(), layoutX, layoutY, xnorGate.getInputAImage(), true);
                adjustLine(xnorGate.getInputBLine(), layoutX, layoutY, xnorGate.getInputBImage(), true);
            }
        }
    }

    public void adjustLine(final Line lineToAdjust, final double x, final double y,
                           final ImageView targetToAdjustWith, final boolean isEnd) {

        if (lineToAdjust == null)
            return;

        if (isEnd) {

            lineToAdjust.setEndX(x + targetToAdjustWith.getLayoutX() + targetToAdjustWith.getLayoutBounds().getWidth() / 2);
            lineToAdjust.setEndY(y + targetToAdjustWith.getLayoutY() + targetToAdjustWith.getLayoutBounds().getHeight() / 2);

        } else {

            lineToAdjust.setStartX(x + targetToAdjustWith.getLayoutX() + targetToAdjustWith.getLayoutBounds().getWidth() / 2);
            lineToAdjust.setStartY(y + targetToAdjustWith.getLayoutY() + targetToAdjustWith.getLayoutBounds().getHeight() / 2);
        }

    }

    private void setDragView(Dragboard dragboard) {
        dragboard.setDragView(new Image("org/example/icons/drag-image.png"));
    }

    public void addListenersToNode(AnchorPane anchorPane) {
        if (anchorPane == null) {
            return;
        }

        if (anchorPane instanceof AndGate) {
            AndGate andGate = (AndGate) anchorPane;
            andGate.getAndGate().setOnDragDetected(this::componentDragDetected);
            andGate.getOutputImage().setOnDragDetected(this::outputDragDetected);
            andGate.getInputAImage().setOnDragOver(this::inputDragOver);
            andGate.getInputAImage().setOnDragEntered(this::inputDragEntered);
            andGate.getInputAImage().setOnDragExited(this::inputDragExited);
            andGate.getInputBImage().setOnDragOver(this::inputDragOver);
            andGate.getInputBImage().setOnDragEntered(this::inputDragEntered);
            andGate.getInputBImage().setOnDragExited(this::inputDragExited);
            andGate.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    pane.getChildren().removeAll(
                            andGate.getOutputLine(),
                            andGate.getInputALine(),
                            andGate.getInputBLine(),
                            andGate
                    );
                }
            });

        } else if (anchorPane instanceof OrGate) {

            OrGate orGate = (OrGate) anchorPane;
            orGate.getOrGate().setOnDragDetected(this::componentDragDetected);
            orGate.getOutputConnector().setOnDragDetected(this::outputDragDetected);
            orGate.getInputAImage().setOnDragEntered(this::inputDragEntered);
            orGate.getInputAImage().setOnDragOver(this::inputDragOver);
            orGate.getInputAImage().setOnDragExited(this::inputDragExited);
            orGate.getInputBImage().setOnDragEntered(this::inputDragEntered);
            orGate.getInputBImage().setOnDragExited(this::inputDragExited);
            orGate.getInputBImage().setOnDragOver(this::inputDragOver);
            orGate.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    pane.getChildren().removeAll(
                            orGate.getOutputLine(),
                            orGate.getInputALine(),
                            orGate.getInputBLine(),
                            orGate
                    );
                }
            });

        } else if (anchorPane instanceof LEDGate) {

            LEDGate led = (LEDGate) anchorPane;
            led.getLEDImage().setOnDragDetected(this::componentDragDetected);
            led.getInputImage().setOnDragEntered(this::inputDragEntered);
            led.getInputImage().setOnDragOver(this::inputDragOver);
            led.getInputImage().setOnDragExited(this::inputDragExited);
            if (sandboxController != null) {
                led.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.SECONDARY) {
                        pane.getChildren().removeAll(
                                led.getInputLine(),
                                led
                        );
                    }
                });
            }

        } else if (anchorPane instanceof NAndGate) {

            NAndGate nAndGate = (NAndGate) anchorPane;
            nAndGate.getNandGate().setOnDragDetected(this::componentDragDetected);
            nAndGate.getOutputImage().setOnDragDetected(this::outputDragDetected);
            nAndGate.getInputAImage().setOnDragOver(this::inputDragOver);
            nAndGate.getInputAImage().setOnDragEntered(this::inputDragEntered);
            nAndGate.getInputAImage().setOnDragExited(this::inputDragExited);
            nAndGate.getInputBImage().setOnDragOver(this::inputDragOver);
            nAndGate.getInputBImage().setOnDragEntered(this::inputDragEntered);
            nAndGate.getInputBImage().setOnDragExited(this::inputDragExited);
            nAndGate.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    pane.getChildren().removeAll(
                            nAndGate.getOutputLine(),
                            nAndGate.getInputALine(),
                            nAndGate.getInputBLine(),
                            nAndGate
                    );
                }
            });

        } else if (anchorPane instanceof PowerGate) {

            PowerGate powerSourceComponent = (PowerGate) anchorPane;
            powerSourceComponent.getPowerSourceImage().setOnDragDetected(this::componentDragDetected);
            powerSourceComponent.getOutputConnector().setOnDragDetected(this::outputDragDetected);
            powerSourceComponent.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    pane.getChildren().removeAll(
                            powerSourceComponent.getOutputLine(),
                            powerSourceComponent
                    );
                }
            });

        } else if (anchorPane instanceof NorGate) {

            NorGate norGateComponent = (NorGate) anchorPane;
            norGateComponent.getNorGate().setOnDragDetected(this::componentDragDetected);
            norGateComponent.getOutputImage().setOnDragDetected(this::outputDragDetected);
            norGateComponent.getInputAImage().setOnDragOver(this::inputDragOver);
            norGateComponent.getInputAImage().setOnDragEntered(this::inputDragEntered);
            norGateComponent.getInputAImage().setOnDragExited(this::inputDragExited);
            norGateComponent.getInputBImage().setOnDragOver(this::inputDragOver);
            norGateComponent.getInputBImage().setOnDragEntered(this::inputDragEntered);
            norGateComponent.getInputBImage().setOnDragExited(this::inputDragExited);
            norGateComponent.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    pane.getChildren().removeAll(
                            norGateComponent.getOutputLine(),
                            norGateComponent.getInputALine(),
                            norGateComponent.getInputBLine(),
                            norGateComponent
                    );
                }
            });

        } else if (anchorPane instanceof NotGate) {

            NotGate notGate = (NotGate) anchorPane;
            notGate.getNotGate().setOnDragDetected(this::componentDragDetected);
            notGate.getOutputImage().setOnDragDetected(this::outputDragDetected);
            notGate.getInputA().setOnDragOver(this::inputDragOver);
            notGate.getInputA().setOnDragEntered(this::inputDragEntered);
            notGate.getInputA().setOnDragExited(this::inputDragExited);
            notGate.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    pane.getChildren().removeAll(
                            notGate.getOutputLine(),
                            notGate.getInputALine(),
                            notGate.getInputBLine(),
                            notGate
                    );
                }
            });

        } else if (anchorPane instanceof XorGate) {

            XorGate xorGate = (XorGate) anchorPane;
            xorGate.getXorGate().setOnDragDetected(this::componentDragDetected);
            xorGate.getOutput().setOnDragDetected(this::outputDragDetected);
            xorGate.getInputAImage().setOnDragOver(this::inputDragOver);
            xorGate.getInputAImage().setOnDragEntered(this::inputDragEntered);
            xorGate.getInputAImage().setOnDragExited(this::inputDragExited);
            xorGate.getInputBImage().setOnDragOver(this::inputDragOver);
            xorGate.getInputBImage().setOnDragEntered(this::inputDragEntered);
            xorGate.getInputBImage().setOnDragExited(this::inputDragExited);
            xorGate.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    pane.getChildren().removeAll(
                            xorGate.getOutputLine(),
                            xorGate.getInputALine(),
                            xorGate.getInputBLine(),
                            xorGate
                    );
                }
            });

        } else if (anchorPane instanceof XnorGate) {
            XnorGate xnorGate = (XnorGate) anchorPane;
            xnorGate.getXnorGate().setOnDragDetected(this::componentDragDetected);
            xnorGate.getOutput().setOnDragDetected(this::outputDragDetected);
            xnorGate.getInputAImage().setOnDragOver(this::inputDragOver);
            xnorGate.getInputAImage().setOnDragEntered(this::inputDragEntered);
            xnorGate.getInputAImage().setOnDragExited(this::inputDragExited);
            xnorGate.getInputBImage().setOnDragOver(this::inputDragOver);
            xnorGate.getInputBImage().setOnDragEntered(this::inputDragEntered);
            xnorGate.getInputBImage().setOnDragExited(this::inputDragExited);
            xnorGate.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    pane.getChildren().removeAll(
                            xnorGate.getOutputLine(),
                            xnorGate.getInputALine(),
                            xnorGate.getInputBLine(),
                            xnorGate
                    );
                }
            });
        }
    }

    public Line createLine(final double startX, final double startY, final double endX,
                           final double endY) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStrokeWidth(3);
        line.setStroke(LINE_COLOR);
        return line;
    }

    public void setLinePosition(final Node target, final Line line) {
        double x = target.getLayoutX() + target.getParent().getParent().getLayoutX() + target.getLayoutBounds().getWidth() / 2;
        double y = target.getLayoutY() + target.getParent().getParent().getLayoutY() + target.getLayoutBounds().getHeight() / 2;

        line.setEndX(x);
        line.setEndY(y);
    }

    public void mapLineTo(final Object target, final Line line, final String fxID) {

        if (target instanceof LEDGate) {

            LEDGate ledComponent = (LEDGate) target;

            if (ledComponent.getInputLine() != null)
                this.pane.getChildren().remove(ledComponent.getInputLine());
            ledComponent.setInputLine(line);

        } else if (target instanceof Gate) {

            Gate binaryGate = (Gate) target;
            switch (fxID) {
                case INPUT_A:
                    if (binaryGate.getInputALine() != null)
                        this.pane.getChildren().remove(binaryGate.getInputALine());
                    binaryGate.setInputALine(line);
                    break;

                case INPUT_B:
                    if (binaryGate.getInputBLine() != null)
                        this.pane.getChildren().remove(binaryGate.getInputBLine());
                    binaryGate.setInputBLine(line);
                    break;

                case "outputConnector":
                    if (binaryGate.getOutputLine() != null)
                        this.pane.getChildren().remove(binaryGate.getOutputLine());
                    binaryGate.setOutputLine(line);
                    break;
            }

        } else if (target instanceof PowerSource) {

            PowerSource powerSource = (PowerSource) target;
            if (powerSource.getOutputLine() != null)
                this.pane.getChildren().remove(powerSource.getOutputLine());
            powerSource.setOutputLine(line);
        }
    }

    public void mapNodeTo(final Parent outputNode, final Parent inputNode, final String fxId) {


        if (outputNode instanceof LEDGate ||
                inputNode instanceof PowerGate ||
                outputNode == null ||
                inputNode == null ||
                !(outputNode instanceof Circuit) ||
                !(inputNode instanceof Circuit)) {
            return;
        }

        Circuit outputComponent = (Circuit) outputNode;
        ((Circuit) outputNode).setOutputNode((Circuit) inputNode);

        if (inputNode instanceof Gate) {
            Gate binaryGate = (Gate) inputNode;
            if (fxId.equals(INPUT_A)) {
                binaryGate.setInputANode(outputComponent);
                outputComponent.setOutputType(PUT_A);
            } else if (fxId.equals(INPUT_B)) {
                binaryGate.setInputBNode(outputComponent);
                outputComponent.setOutputType(PUT_B);
            }
        } else if (inputNode instanceof LEDGate) {
            LEDGate ledComponent = (LEDGate) inputNode;
            ledComponent.setInputANode(outputComponent);
            outputComponent.setOutputType(PUT_A);
        }
    }
}
