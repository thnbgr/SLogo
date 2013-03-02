package parser.node.control;
import parser.node.UnaryNode;

public class MakeNode extends UnaryNode{
	private String myName;
	public MakeNode(String name) {
		super();
		myName = name;
	}
	public String getName(){
		return myName;
	}
	

}
