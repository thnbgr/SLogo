package parser.node.logic;

import parser.node.Node;

/**
 * This class deals with the not command
 * @author junho
 */
public class NotNode extends Node {

    /**
     * This method sets the value of the node opposite to the value of
     * the child
     */
    @Override
    public void setReturnValue() {
        if (getChildren().get(0).getValue() == 0) {
            super.setValue(1);
        }
        else {
            super.setValue(0);
        }
    }

}
