package parser.node.control;

import parser.node.Node;
import java.util.ArrayList;

public class CustomCommandNode extends Node {
	private ArrayList<String> myVariableNames;
	private String myName;
	private String myCustomCommand;
	
	public CustomCommandNode() {
		myName = new String();
		myVariableNames = new ArrayList<String>();
	}
	
	public void addVarName(String varName){
		myVariableNames.add(varName);
	}
	public void setName(String name){
		myName = name;
	}
	public void setCustomCommand(String custCommand){
		myCustomCommand = custCommand;
	}
	public ArrayList<String> getVarNames(){
		return myVariableNames;
	}
	public String getName(){
		return myName;
	}
	public String getCommand(){
		return myCustomCommand;
	}
}
