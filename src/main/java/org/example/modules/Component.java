package org.example.modules;

import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Component extends AnchorPane {
    private Boolean firstInputValue;
    private Boolean outputValue;
    private Component firstInput;
    private Component firstOutput;
    //private Enum output;

    public Component(final boolean firstInputValue, final boolean outputValue) {
        this.firstInputValue = firstInputValue;
        this.outputValue = outputValue;
    }
    public Component(final boolean firstInputValue) {
        this.firstInputValue = firstInputValue;
        this.outputValue = false;
    }
    public Component() {
        this.firstInputValue = false;
        this.outputValue = false;
    }
    public boolean hasFirstInputValue() {
        return firstInputValue != null;
    }

    public boolean hasOutputInputValue() {
        return outputValue != null;
    }

}
