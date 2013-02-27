package parser.node;

//interface Container{}

/*
class DoubleContainer implements Container
{
	double myValue;
	public void setValue(double value){
		myValue = value;
	}
	public double returnValue(){
		return myValue;
	}
}
class VoidContainer implements Container
{
}

class BooleanContainer implements Container
{
	
}*/

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
	public void evaluate(){}
}


