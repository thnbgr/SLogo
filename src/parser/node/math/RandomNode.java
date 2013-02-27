package parser.node.math;

import java.util.Random;

import parser.node.Container;
import parser.node.UnaryNode;
import parser.node.Node;

public class RandomNode extends UnaryNode {

	public RandomNode(Node head) {
		super(head);
	}

	@Override
	public void evaluate(){
		Container<Integer> myContainer = new Container<Integer>();
		int childValue = ((Container<Double>)getChild().getContainer()).getValue().intValue();
		Random r = new Random();
		myContainer.setValue(r.nextInt(childValue));
		setContainer(myContainer);
	}
}
