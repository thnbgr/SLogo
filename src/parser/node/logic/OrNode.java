package parser.node.logic;

import parser.node.Node;

/**
 * This class deals with the or command
 * @author junho
 */
public class OrNode extends Node {

    /**
     * This method sets the return value of this node
     * to be either 1 if the values of one child or the other is 1,
     * else set to zero
     */
    @Override
    public void setReturnValue() {
        if (getChildren().get(0).getValue() == 1 || getChildren().get(1).getValue() == 1) {
            super.setValue(1);
        }
        else {
            super.setValue(0);
        }
    }

}
