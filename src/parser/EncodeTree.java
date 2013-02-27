package parser;

import parser.node.*;
public class EncodeTree {
	Node myHead;

	public EncodeTree(){
		myHead = null;
	}
	public Node getHead()
	{
		return myHead;
	}
	public EncodeTree(Node head){
		myHead = head;
	}
}