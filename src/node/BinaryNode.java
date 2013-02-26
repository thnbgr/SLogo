package node;
/*
 * @author Junho Oh
 */
public class BinaryNode extends Node{
	private IDoubleNode myLeft;
	private IDoubleNode myRight;
	
	public BinaryNode(Node head) {
		super(head);
	}
	public void setLeft(IDoubleNode left){
		myLeft = left;
	}
	public void setRight(IDoubleNode right){
		myRight = right;
	}
	public IDoubleNode getLeft(){
		return myLeft;
	}
	public IDoubleNode getRight(){
		return myRight;
	}
	public void test()
	{
		System.out.println("HI");
	}
}
