package parser.node.turtleCommand;

import parser.node.Node;

/**
 * This class deals with all turtle commands
 * such as forward, backward, left, right
 * setxy, towards
 * @author junho
 *
 */
public class TurtleCommandNode extends Node {
    private String myName;

    /**
     * This is the constructor that initializes the name to a new string
     */
    public TurtleCommandNode() {
        myName = new String();
    }
    /**
     * Creates a string of the name of the turtle command and the value associated
     * with the command
     */
    public String toString() {
        String childrenValues = new String();
        for (Node child : getChildren()) {
            childrenValues += " " + child.getValue();
        }
        return myName + childrenValues;
    }
    /**
     * Sets the name of the turtlecommand
     * @param name - the name of the turtle command
     */
    public void setName(String name) {
        myName = name;
    }
}
