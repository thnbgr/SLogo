package parser.node.control;

import parser.node.BinaryNode;
import parser.node.Container;
import parser.node.Node;
import java.util.Queue;

public class RepeatNode extends BinaryNode {

	public RepeatNode(Node head) {
		super(head);
		// TODO Auto-generated constructor stub
	}
	public void makeTree(Node token, Queue<Node> tokens){
		
	}
	@Override
	public void evaluate(){
		//move this to model. or have an arraylist as value of this node
		Container<Double> myContainer = new Container<Double>();
		int count = 0;
		getLeft().evaluate();
		if(getLeft().getContainer().getValue() instanceof Double){
			count = (Integer) getLeft().getContainer().getValue();
		}
		while(count >0){
			getRight().getContainer().getValue();
			this.evaluate();
		}
		setContainer(myContainer);
	}
	
}
