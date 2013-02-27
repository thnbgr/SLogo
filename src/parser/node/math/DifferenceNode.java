package parser.node.math;

import parser.node.*;
/**
 * 
 * @author Junho Oh
 */
public class DifferenceNode extends MathNode{

	public DifferenceNode(Node head) {
		super(head);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void setContainerValue(Container<Double> container){
		container.setValue(((Container<Double>)getLeft().getContainer()).getValue() - ((Container<Double>)getRight().getContainer()).getValue());
	}
}
