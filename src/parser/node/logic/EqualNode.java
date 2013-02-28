package parser.node.logic;

import parser.node.Container;
import parser.node.Node;

public class EqualNode extends LogicNode {

	public EqualNode(Node head) {
		super(head);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setContainerValue(Container<Boolean> container) {
		container.setValue(((Container<Double>)getLeft().getContainer()).getValue() == ((Container<Double>)getRight().getContainer()).getValue());
		
	}

}
