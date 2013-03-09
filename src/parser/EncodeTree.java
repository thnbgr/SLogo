package parser;

import parser.node.*;
/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class EncodeTree {
	Node myHead;

	public EncodeTree(){
		myHead = null;
	}
	public EncodeTree(Node head){
		myHead = head;
	}
	public Node getHead()
	{
		return myHead;
	}

}