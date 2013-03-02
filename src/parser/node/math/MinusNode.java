package parser.node.math;

import parser.node.Container;
import parser.node.UnaryNode;
import parser.node.Node;
/**
 * 
 * @author Junho Oh
 */
public class MinusNode extends UnaryNode {

	public MinusNode() {
	}
	@Override
	public void evaluate(){
		Container<Double> myContainer = new Container<Double>();
		myContainer.setValue(-((Container<Double>)getChild().getContainer()).getValue());
		setContainer(myContainer);
	}
}
