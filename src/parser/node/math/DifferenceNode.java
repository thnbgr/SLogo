package parser.node.math;

import parser.node.*;

public class DifferenceNode extends BinaryNode{

	public DifferenceNode(Node head) {
		super(head);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void evaluate(){
		//System.out.println("evaluating");
		Container<Double> myContainer = new Container<Double>();
		getLeft().evaluate();
		getRight().evaluate();
		myContainer.setValue((((Container<Double>)getLeft().getContainer()).getValue() - ((Container<Double>)getRight().getContainer()).getValue()));
		setContainer(myContainer);
		//System.out.println(getContainer().getValue());
	}

}
