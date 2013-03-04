package parser.node.turtleCommand;

import parser.node.Node;

public class TurtleCommandNode extends Node {
	private String myName;
	public TurtleCommandNode(){
		myName = new String();
	}
	public String toString(){
		return myName + " " + getChildren().get(0).getValue();
	}
	public void setName(String name){
		myName = name;
	}
	@Override
	public void setOperation(){
		setValue(getChildren().get(0).getValue());
	}
}
