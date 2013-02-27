package parser.node.math;

import parser.node.BinaryNode;
import parser.node.Container;

import parser.node.Node;
/**
 * 
 * @author Junho Oh
 */
public class ProductNode extends MathNode{

	public ProductNode(Node head) {
		super(head);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setContainerValue(Container<Double> container){
		container.setValue(((Container<Double>)getLeft().getContainer()).getValue() * ((Container<Double>)getRight().getContainer()).getValue());
	}

}
