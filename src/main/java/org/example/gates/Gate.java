package org.example.gates;

import org.example.modules.Component;

public class Gate extends Component {
    public Gate(boolean inputAValue, final boolean inputBValue, final boolean output, final int gateTypeId) {
        super(inputAValue, output);
        this.inputBValue = inputBValue;
        this.gateTypeId = gateTypeId;
    }
}
