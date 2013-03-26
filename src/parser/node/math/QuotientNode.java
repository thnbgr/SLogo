package parser.node.math;

import parser.node.Node;

/**
 * This class deals with the quotient command
 * @author Junho Oh
 */
public class QuotientNode extends Node {

    /**
     * Sets the value of the node as the quotient of the left child's value
     * and the right child's value. 
     * throws IllegalArgumentException if the right child's value is zero
     */
    @Override
    public void setReturnValue() {
        if (getChildren().get(1).getValue() == 0) {
            throw new IllegalArgumentException();
        }
        else {
            setValue(getChildren().get(0).getValue() / getChildren().get(1).getValue());
        }
    }
}
