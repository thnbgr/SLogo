package parser.node;

/**
 * This class is a subclass of Node that deals with nodes that 
 * are just integers
 * @author Junho Oh
 */
public class ValueNode extends Node {
    /**
     * this is the constructor of the ValueNode that sets the 
     * given int value to the value of the node
     * @param value - int value used to set node's value to 
     */
    public ValueNode(int value) {
        setValue(value);
    }
}
