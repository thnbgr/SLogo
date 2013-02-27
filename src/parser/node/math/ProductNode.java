package parser.node.math;

import parser.node.BinaryNode;
import parser.node.Container;

import parser.node.Node;
/**
 * 
 * @author Junho Oh
 */
public class ProductNode extends BinaryNode{

	public ProductNode(Node head) {
		super(head);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluate() {
		Container<Double> myContainer = new Container<Double>();
		getLeft().evaluate();
		getRight().evaluate();
		myContainer.setValue((((Container<Double>)getLeft().getContainer()).getValue() * ((Container<Double>)getRight().getContainer()).getValue()));
		setContainer(myContainer);
	}

}
