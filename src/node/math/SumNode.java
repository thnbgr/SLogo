package node.math;

import node.*;

public class SumNode extends BinaryNode implements IDoubleNode{
	public SumNode(Node head) {
		super(head);
	}
	@Override
	public double evaluate(){
		//left + right 
		return getLeft().evaluate() + getRight().evaluate();
	}
}
