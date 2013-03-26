package parser.node.math;

import parser.node.Node;
/**
 * This class deals with the difference command
 * @author Junho Oh
 */
public class DifferenceNode extends Node {

    /**
     * Sets the value as the difference between the value of the 
     * left child and the right child
     */
    @Override
    public void setReturnValue() {
        super.setValue(getChildren().get(0).getValue() - getChildren().get(1).getValue());
    }
}
