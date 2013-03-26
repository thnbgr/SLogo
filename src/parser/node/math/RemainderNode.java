package parser.node.math;

import parser.node.Node;
/**
 * This class deals with the remainder command
 * @author Junho Oh
 */
public class RemainderNode extends Node {

    /**
     * sets the value to be the mod value of the left child's value and right child's 
     * value
     */
    @Override
    public void setReturnValue() {
        super.setValue(getChildren().get(0).getValue() % getChildren().get(1).getValue());
    }
}
