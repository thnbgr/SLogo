package encode.parser;

class Node{
	String myStringValue;
	double myDoubleValue;
	Node myLeft;
	Node myRight;

	public Node(double doubleValue){
		myDoubleValue = doubleValue;
	}

	public Node(String stringValue){
		myStringValue = stringValue;
	}
}

public class EncodeTree {
	Node myHead;

	public EncodeTree(){
		myHead = null;
	}
	public Node getHead()
	{
		return myHead;
	}

	public EncodeTree(String headValue){
		myHead.myStringValue = headValue;
		myHead.myLeft = null;
		myHead.myRight = null;
	}
}