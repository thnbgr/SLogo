package parser.node.math;

import parser.node.Node;
/**
 * This class deals with the minus command
 * @author Junho Oh
 */
public class MinusNode extends Node {

    /**
     * sets the value as the negative of the child's value
     */
    @Override
    public void setReturnValue() {
        super.setValue(-getChildren().get(0).getValue());
    }
}
