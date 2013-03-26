package parser;

import parser.node.*;
/**
 *
 * @author Junho Oh
 * @author Wenshun Liu
 *
 *		This class represents the structure of the tree that the user input
 *		command will be parsed into and sent to Model to decode.
 */
public class EncodeTree {
	Node myHead;

	public EncodeTree() {
		myHead = null;
	}
	public EncodeTree(Node head) {
		myHead = head;
	}
	public Node getHead() {
		return myHead;
	}

}