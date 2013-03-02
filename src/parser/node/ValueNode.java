package parser.node;

/**
 * 
 * @author Junho Oh
 */
public class ValueNode extends Node{
	public ValueNode(double value) {
		Container<Double> myContainer = new Container<Double>();
		myContainer.setValue(value);
		setContainer(myContainer);
	}
}
