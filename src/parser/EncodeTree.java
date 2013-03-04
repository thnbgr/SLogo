package parser;

import parser.node.*;
/**
 * 
 * @author Junho Oh
 */
public class EncodeTree {
	private Node myHead;

	public EncodeTree(){
		myHead = null;
	}
	public EncodeTree(Node head){
		myHead = head;
	}
	public Node getHead(){
		return myHead;
	}
	public void evaluate(){
		myHead.evaluate();
	}

}