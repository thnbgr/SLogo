package parser.node.turtleCommand;

import parser.node.Node;

public class TurtleCommandNode extends Node {
	private String myName;
	
	public TurtleCommandNode(String commandName){
		myName = commandName;
	}
	
	@Override
	public void setValue(){
		getChildren().get(0).setValue();
	}
	
	public String getName(){
		return myName;
	}

}
