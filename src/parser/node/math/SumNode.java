package parser.node.math;

import parser.node.*;
/**
 * 
 * @author Junho Oh
 */
public class SumNode extends MathNode{
	public SumNode() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setContainerValue(Container<Double> container){
		container.setValue(((Container<Double>)getLeft().getContainer()).getValue() + ((Container<Double>)getRight().getContainer()).getValue());
	}

}
