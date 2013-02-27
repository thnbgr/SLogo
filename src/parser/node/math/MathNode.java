package parser.node.math;
import parser.node.BinaryNode;
import parser.node.Container;
import parser.node.Node;
/**
 * 
 * @author Junho Oh
 */
public abstract class MathNode extends BinaryNode {

	public MathNode(Node head) {
		super(head);
	}

	@Override
	public void evaluate(){
		Container<Double> myContainer = new Container<Double>();
		evaluateChildren();
		setContainerValue(myContainer);
		setContainer(myContainer);
	}
	public abstract void setContainerValue(Container<Double> container);
}
