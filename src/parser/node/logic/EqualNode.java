package parser.node.logic;

import parser.node.Node;

/**
 * This class deals with the equal? command
 * @author junho oh
 *
 */
public class EqualNode extends Node {

    /**
     * This method sets the return value of this node
     * to be either 1 if the values of both children are equal,
     * else set to zero
     */
    @Override
    public void setReturnValue() {
        if (getChildren().get(0).getValue() == getChildren().get(1).getValue()) {
            super.setValue(1);
        }
        else {
            super.setValue(0);
        }
    }
}
