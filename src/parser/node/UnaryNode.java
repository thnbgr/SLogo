package parser.node;

public class UnaryNode extends Node {
	Node myChild;
	public UnaryNode(Node head) {
		super(head);
		myChild = new Node();
	}
	public void setchild(Node child){
		myChild = child;
	}
	
}