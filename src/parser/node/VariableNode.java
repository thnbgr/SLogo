package parser.node;

public class VariableNode extends Node {
	private String myName;
	public VariableNode() {
	}
	public VariableNode(int value, String name){
		setValue(value);
		myName = name;
	}
	public String getName(){
		return myName;
	}
}
