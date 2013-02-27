package parser.node.math;

import parser.node.BinaryNode;
import parser.node.Container;
import parser.node.Node;
/**
 * 
 * @author Junho Oh
 */
public class QuotientNode extends MathNode{

	public QuotientNode(Node head) {
		super(head);

	}
	@SuppressWarnings("unchecked")
	@Override
	public void setContainerValue(Container<Double> container){
		container.setValue(((Container<Double>)getLeft().getContainer()).getValue() / ((Container<Double>)getRight().getContainer()).getValue());
	}
}
