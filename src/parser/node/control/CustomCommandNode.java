package parser.node.control;

import java.util.ArrayList;
import parser.node.Node;

/**
 * This class deals with custom commands by having a list of commands that
 * is associated with the custom command as well as a list of local
 * variables it can use
 * @author junho oh
 * @author Wenshun Liu 
 */
public class CustomCommandNode extends Node {
    private ArrayList<String> myVariableNames;
    private String myName;
    private ArrayList<String> myCustomCommand;

    /**
     * this is the constructor which initializes myName and myVariableNames
     */
    public CustomCommandNode() {
        myName = new String();
        myVariableNames = new ArrayList<String>();
    }

    /**
     * This is another constructor which takes in a list of parameters
     * and sets the local variables to their respective input values
     * @param name - the name of the custom command
     * @param variableNames - the list of variable names
     * @param customCommand - the list of commands to execute
     */
    public CustomCommandNode(String name, ArrayList<String> variableNames, 
                             ArrayList<String> customCommand) {
        myName = name;
        myCustomCommand = customCommand;
        myVariableNames = variableNames;
    }
    /**
     * This method adds in a variable name to the local variable name list
     * @param varName - the string variable name to be added to the list
     */
    public void addVarName(String varName) {
        myVariableNames.add(varName);
    }
    /**
     * This method sets the name of the custom command to the input string
     * @param name - the string to set the name of the custom command to
     */
    public void setName(String name) {
        myName = name;
    }
    /**
     * This method sets the list of commands to execute to the input
     * list of commands
     * @param custCommand - the list of commands to be used to set
     * as the list of commands to execute
     */
    public void setCustomCommand(ArrayList<String> custCommand) {
        myCustomCommand = custCommand;
    }
    /**
     * this method is used to return the list of variable names 
     * associated to this custom command
     * @return returns the list of variable names
     */
    public ArrayList<String> getVarNames() {
        return myVariableNames;
    }
    /**
     * this method is used to return the name of the custom command
     * @return returns the name of the custom command
     */
    public String getName() {
        return myName;
    }
    /**
     * this method is used to return the list of custom commands to 
     * execute
     * @return returns the list of commands to execute
     */
    public ArrayList<String> getCommand() {
        return myCustomCommand;
    }
}
