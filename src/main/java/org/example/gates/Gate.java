package org.example.gates;

import javafx.scene.shape.Line;
import lombok.Getter;
import lombok.Setter;
import org.example.modules.Component;
@Setter
@Getter
public class Gate extends Component {
    private Boolean secondInputValue;
    private Component secondOutput;
    private GateType gateType;
    private Line firstInputWire, secondInputWire, outputWire;
    public Gate(boolean firstInputValue, final boolean secondInputValue, final boolean Output, GateType gateType) {
        super(firstInputValue, Output);
        this.secondInputValue = secondInputValue;
        this.gateType = gateType;
    }
}
