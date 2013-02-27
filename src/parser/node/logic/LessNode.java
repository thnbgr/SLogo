package parser.node.logic;

import parser.node.BinaryNode;
import parser.node.Container;
import parser.node.Node;
/**
 * 
 * @author Junho Oh
 */
public class LessNode extends LogicNode {

	public LessNode(Node head) {
		super(head);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setContainerValue(Container<Boolean> container) {
		container.setValue(((Container<Double>)getLeft().getContainer()).getValue() - ((Container<Double>)getRight().getContainer()).getValue());
	}
	
}
