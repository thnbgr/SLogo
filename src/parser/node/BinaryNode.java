package parser.node;
/*
 * @author Junho Oh
 */
public class BinaryNode extends Node{
	private Node myLeft;
	private Node myRight;
	
	public BinaryNode(Node head) {
		super(head);
	}
	public void setChildren(Node left, Node right){
		myLeft = left;
		myRight = right;
	}
	public Node getLeft(){
		return myLeft;
	}
	public Node getRight(){
		return myRight;
	}
	public void evaluateChildren(){
		myLeft.evaluate();
		myRight.evaluate();
	}
}
