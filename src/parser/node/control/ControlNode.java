package parser.node.control;

import java.util.ArrayList;
import parser.node.Node;
import parser.node.turtleCommand.TurtleCommandNode;

/**
 * this class is a subclass of Node that is the superclass 
 * for all of the control structure nodes (commands) 
 * @author junho oh
 */
public class ControlNode extends Node {
    private ArrayList<String> myReturnCommands;

    /**
     * this is the constructor of the class that initializes
     * the return commands, which are the list of string
     * of commands to return
     */
    public ControlNode() {
        myReturnCommands = new ArrayList<String>();
    }

    /**
     * this returns the list of return commands
     * @return myReturnCommands - the array list
     * of return commands 
     */
    public ArrayList<String> getReturnCommands() {
        return myReturnCommands;
    }

    /**
     * this method adds a command to the return command only
     * if the given command is an instance of turtle command
     * @param command - a Node 
     */
    private void addReturnCommand(Node command) {
        if (command instanceof TurtleCommandNode) {
            System.out.println(((TurtleCommandNode)command).toString());
            myReturnCommands.add(((TurtleCommandNode)command).toString());
        }
    }

    /**
     * This method evaluates the given list of children and 
     * calls addReturnCommand. It also sets the value of the 
     * node to the value of the last command executed
     * @param children - arraylist of node children
     */
    public void evaluateChildren(ArrayList<Node> children) {
        for (Node command : children) {
            command.evaluate();
            addReturnCommand(command);
            System.out.println(command.getValue());
        }
        int childSize = children.size();
        setValue(children.get(childSize - 1).getValue());
    }

}
