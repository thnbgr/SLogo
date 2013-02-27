package parser.node;
/**
 * 
 * @author Junho Oh
 */
public class ValueNode extends Node{
	public ValueNode(Node head, double value) {
		super(head);
		Container<Double> myContainer = new Container<Double>();
		myContainer.setValue(value);
		setContainer(myContainer);
	}
}
