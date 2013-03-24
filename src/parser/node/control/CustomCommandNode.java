package parser.node.control;

import parser.node.Node;
import java.util.ArrayList;

public class CustomCommandNode extends Node {
	private ArrayList<String> myVariableNames;
	private String myName;
	private ArrayList<String> myCustomCommand;
	
	public CustomCommandNode() {
		myName = new String();
		myVariableNames = new ArrayList<String>();
	}
	
	public CustomCommandNode(String name, ArrayList<String> variableNames, ArrayList<String> customCommand){
		myName = name;
		myCustomCommand = customCommand;
		myVariableNames = variableNames;
	}
	
	public void addVarName(String varName){
		myVariableNames.add(varName);
	}
	public void setName(String name){
		myName = name;
	}
	public void setCustomCommand(ArrayList<String> custCommand){
		myCustomCommand = custCommand;
	}
	public ArrayList<String> getVarNames(){
		return myVariableNames;
	}
	public String getName(){
		return myName;
	}
	public ArrayList<String> getCommand(){
		return myCustomCommand;
	}
}
