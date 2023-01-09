package org.example.model;

import javafx.scene.shape.Line;


public class Gate extends Circuit implements GateName {


    private boolean inputBValue;

    private Circuit inputBNode;

    private Line inputALine;

    private Line inputBLine;

    private Line outputLine;

    private int gateTypeId;


    public Gate(final boolean inputAValue, final boolean inputBValue, final boolean output, final int gateTypeId) {
        super(inputAValue, output);
        this.inputBValue = inputBValue;
        this.gateTypeId = gateTypeId;
    }


    public void setInputBValue(boolean inputBValue) {
        this.inputBValue = inputBValue;
    }

    public void setInputALine(Line inputALine) {
        this.inputALine = inputALine;
    }

    public void setInputBLine(Line inputBLine) {
        this.inputBLine = inputBLine;
    }

    public void setOutputLine(Line outputLine) {
        this.outputLine = outputLine;
    }

    public void setInputBNode(Circuit inputBNode) {
        this.inputBNode = inputBNode;
    }

    public Line getOutputLine() {
        return outputLine;
    }

    public Line getInputALine() {
        return inputALine;
    }

    public Line getInputBLine() {
        return inputBLine;
    }

    public boolean getInputBValue() {
        return this.inputBValue;
    }

}
