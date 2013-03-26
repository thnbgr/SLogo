package parser.node.control;

import java.util.ArrayList;
import parser.node.Node;

public class ControlNode extends Node {
	private ArrayList<String> myReturnCommands;
	
	public ControlNode(){
		myReturnCommands = new ArrayList<String>();
	}
	
	public ArrayList<String> getReturnCommands(){
		return myReturnCommands;
	}
	
}
