package parser.node.control;

import parser.node.Node;

public class MakeNode extends Node{
	private String myName;
	public MakeNode(String name) {
		super();
		myName = name;
	}
	public String getName(){
		return myName;
	}
	

}
