package parser.node.math;

import parser.node.*;

public class DifferenceNode extends BinaryNode implements IDoubleNode{

	public DifferenceNode(Node head) {
		super(head);
	}
	@Override
	public double evaluate(){
		//left + right 
		//difference 10 5 = 10 - 5? 
		return getLeft().evaluate() - getRight().evaluate();
	}

}
