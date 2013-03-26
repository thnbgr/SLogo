package parser.node;

import java.util.Queue;
import java.util.ArrayList;

/**
 * @author Junho Oh
 * This class is the super class for all nodes (commands)
 */
public class Node {
	private int myValue;
	private int myNumArgs;
	private ArrayList<Node> myChildren;
	/**
	 * constructor for the Node class. Initializes myChildren ArrayList
	 */
	public Node(){
		myChildren = new ArrayList<Node>();
	}
	/**
	 * adds a child node to the children array list. 
	 * @param child - a child Node which will be added to the ArrayList myChildren
	 */
	public void addChild(Node child){
		myChildren.add(child);
	}
	/**
	 * sets the number of arguments/children 
	 * @param numArgs - the int value of the number of arguments (number of children)
	 * this node will take
	 */
	public void setNumArgs(int numArgs){
		myNumArgs = numArgs;
	}
	/**
	 * sets the value 
	 * @param value - the int value that will be used to set value
	 */
	public void setValue(int value){
		myValue = value;
	}
	/**
	 * empty class that will be overwritten by subclasses which sets the value of the node
	 * that will be returned. 
	 */
	public void setReturnValue(){}
	/**
	 * returns the value associated with this node
	 * @return myValue the int value of the node
	 */
	public int getValue(){
		return myValue;
	}
	/**
	 * returns the list of children
	 * @return myChildren - an arrayList of current node's children
	 */
	public ArrayList<Node> getChildren(){
		return myChildren;
	}
	/**
	 * This method calls the evaluate function for all of this node's children and also calls
	 * the setReturnValue method that will be overwritten for each subclass
	 */
	public void evaluate(){
		for(Node child : getChildren()){
			child.evaluate();
		}
		setReturnValue();
	}
	
	/**
	 * This method is a recursive implementation of making a syntax tree based on the number of
	 * arguments this node requires
	 * @param tokens is the Queue of nodes to be used to make a valid syntax tree 
	 */
	public void makeTree(Queue<Node> tokens){
		for(int i = 0; i < myNumArgs; i++){
			Node child = tokens.remove();
			addChild(child);
			child.makeTree(tokens);
		}
	}
}


