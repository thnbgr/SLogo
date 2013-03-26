package parser.node;

/**
 * This class is a subclass of Node that deals with variable nodes
 * @author Junho Oh
 */
public class VariableNode extends Node {
    private String myName;

    /**
     * This is the constructor which sets the value and name 
     * of the node
     * @param value - int value to set the node's value to
     * @param name - name to set the node's variable name to
     */
    public VariableNode(int value, String name) {
        setValue(value);
        myName = name;
    }
    /**
     * this method returns the name of the variable
     * @return myName - the name of the variable
     */
    public String getName() {
        return myName;
    }
}
