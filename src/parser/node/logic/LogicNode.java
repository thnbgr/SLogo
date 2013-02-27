package parser.node.logic;

import parser.node.BinaryNode;
import parser.node.Container;
import parser.node.Node;

public abstract class LogicNode extends BinaryNode {

	public LogicNode(Node head) {
		super(head);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluate(){
		Container<Boolean> myContainer = new Container<Boolean>();
		getLeft().evaluate();
		getRight().evaluate();
		setContainerValue(myContainer);
		setContainer(myContainer);
	}
	public abstract void setContainerValue(Container<Boolean> container);
}
