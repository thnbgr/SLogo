package parser.node.math;

import parser.node.Node;
/**
 * This class deals with the product command 
 * @author Junho Oh
 */
public class ProductNode extends Node {

    /**
     * Sets the value as the product of the two children's values
     */
    @Override
    public void setReturnValue() {
        super.setValue(getChildren().get(0).getValue() * getChildren().get(1).getValue());
    }

}
