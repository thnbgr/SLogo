package parser.node.math;

import parser.node.BinaryNode;
import parser.node.Container;
import parser.node.Node;
/**
 * 
 * @author Junho Oh
 */
public class RemainderNode extends MathNode {
	//should be extension of quotient? 
	public RemainderNode(Node head) {
		super(head);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setContainerValue(Container<Double> container){
		container.setValue(((Container<Double>)getLeft().getContainer()).getValue() % ((Container<Double>)getRight().getContainer()).getValue());
	}
}
