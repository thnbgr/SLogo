package parser.node;

public class ValueNode extends Node implements IDoubleNode{
	private double myValue;
	
	public ValueNode(Node head, double value) {
		super(head);
		myValue = value;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double evaluate(){
		return myValue;
	}

}
