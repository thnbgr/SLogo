package parser.node.math;

import parser.node.Node;
/**
 * This class deals with the sum command
 * @author Junho Oh
 */
public class SumNode extends Node {

    /**
     * Sets the value as the sum of the children's values
     */
    @Override
    public void setReturnValue() {
        super.setValue(getChildren().get(0).getValue() + getChildren().get(1).getValue());
    }
}
