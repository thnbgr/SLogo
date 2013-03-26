package parser.node.control;

import java.util.ArrayList;
import parser.node.Node;
import parser.node.turtleCommand.TurtleCommandNode;

public class ControlNode extends Node {
	private ArrayList<String> myReturnCommands;
	
	public ControlNode(){
		myReturnCommands = new ArrayList<String>();
	}
	
	public ArrayList<String> getReturnCommands(){
		return myReturnCommands;
	}
	public void addReturnCommand(Node command){
		if(command instanceof TurtleCommandNode){
			System.out.println(((TurtleCommandNode)command).toString());
			myReturnCommands.add(((TurtleCommandNode)command).toString());
		}
	}
	
}
