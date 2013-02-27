package parser.node.math;
import parser.node.BinaryNode;
import parser.node.Node;

public abstract class MathNode extends BinaryNode {

	public MathNode(Node head) {
		super(head);
	}
	
	//this class will be used to remove redundant code in evaluate
	
	@Override
	public void evaluate(){
		
	}

}
