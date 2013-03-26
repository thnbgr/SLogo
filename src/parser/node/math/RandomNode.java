package parser.node.math;

import java.util.Random;
import parser.node.Node;

/**
 * This class deals with the random command
 * @author junho
 *
 */
public class RandomNode extends Node {

    /**
     * Sets the value as the random number generated from zero to the child's value
     */
    @Override
    public void setReturnValue() {
        Random r = new Random();
        super.setValue(r.nextInt(getChildren().get(0).getValue()));
    }
}
