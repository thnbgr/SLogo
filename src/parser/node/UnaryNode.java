package parser.node;
import java.util.Queue;
/**
 * 
 * @author Junho Oh
 */
public class UnaryNode extends Node {
	Node myChild;
	public UnaryNode(Node head) {
		super(head);
		myChild = new Node();
	}
	public void setchild(Node child){
		myChild = child;
	}
	public Node getChild(){
		return myChild;
	}
	@Override
	public void makeTree(Queue<Node> tokens){
		Node child = tokens.remove();
		child.makeTree(tokens);
		this.setchild(child);
	}
}
