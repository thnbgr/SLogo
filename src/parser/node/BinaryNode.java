package parser.node;

import java.util.Queue;
/*
 * @author Junho Oh
 */
public class BinaryNode extends Node{
	private Node myLeft;
	private Node myRight;
	public BinaryNode(){
		myLeft = new Node();
		myRight = new Node();
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
	@Override
	public void makeTree(Queue<Node> tokens){
		Node left = tokens.remove();
		left.makeTree(tokens);
		Node right = tokens.remove();
		right.makeTree(tokens);
		this.setChildren(left, right);
	}
}
