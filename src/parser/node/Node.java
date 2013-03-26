package parser.node;

import java.util.Queue;
import java.util.ArrayList;

/**
 * 
 * @author Junho Oh
 */
public class Node {
	private Node myHead;
	//private Container<?> myContainer; 
	//no more generics :(
	private int myValue;
	private int myNumArgs;
	private ArrayList<Node> myChildren;
	
	public Node(){
		myHead = null;
		myChildren = new ArrayList<Node>();
	}
	
//	public void setContainer(Container<?> container){ myContainer = container; }
//	public Container<?> getContainer(){ return myContainer; }
	public void addChild(Node child){
		myChildren.add(child);
	}
	public void setNumArgs(int numArgs){
		myNumArgs = numArgs;
	}
	public void setValue(int value){
		myValue = value;
	}
	public void setReturnValue(){}
	public int getValue(){
		return myValue;
	}
	public Node getHead(){ return myHead; }
	public ArrayList<Node> getChildren(){
		return myChildren;
	}
	public void evaluate(){
		for(Node child : getChildren()){
			child.evaluate();
		}
		setReturnValue();
	}
	public void makeTree(Queue<Node> tokens){
		for(int i = 0; i < myNumArgs; i++){
			Node child = tokens.remove();
			addChild(child);
			child.makeTree(tokens);
		}
	}


    public void setMyValue (int myValue) {
        this.myValue = myValue;
    }
}


