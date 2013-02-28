package parser.node.logic;

import parser.node.Container;
import parser.node.Node;
import parser.node.UnaryNode;

public class NotNode extends UnaryNode {

	public NotNode(Node head) {
		super(head);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void evaluate(){
		Container<Boolean> myContainer = new Container<Boolean>();
		getChild().evaluate();
		myContainer.setValue(!((Container<Boolean>)getChild().getContainer()).getValue());
		setContainer(myContainer);
	}

}
