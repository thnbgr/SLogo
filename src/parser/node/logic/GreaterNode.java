package parser.node.logic;

import parser.node.Node;

/**
 * This class deals with the greater? command
 * @author junho oh
 */
public class GreaterNode extends Node {

    /**
     * This method sets the return value of this node
     * to be either 1 if the value of the left child is greater than 
     * the value of the right child,
     * else set to zero
     */
    @Override
    public void setReturnValue() {
        if (getChildren().get(0).getValue() > getChildren().get(1).getValue()) {
            super.setValue(1);
        }
        else {
            super.setValue(0);
        }
    }

}
