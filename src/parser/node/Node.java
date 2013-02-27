package parser.node;
/**
 * 
 * @author Junho Oh
 */
public class Node {
	private Node myHead;
	private Container<?> myContainer;
	
	public Node(Node head) {
		myHead = head;
	}
	public Node(){
		myHead = null;
	}
	public void setContainer(Container<?> container){ myContainer = container; }
	public Container<?> getContainer(){ return myContainer; }
	public Node getHead(){ return myHead; }
	public void evaluate(){}
}


