package model;

import controller.Controller;
import parser.EncodeTree;
import parser.node.Node;
import parser.node.turtleCommand.*;
import parser.node.control.IfElseNode;
import parser.node.control.IfNode;

/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class Model {
	private Controller myController;
	
	public void setController(Controller controller){
		myController = controller;
	}
	
	public Controller getController() {
		return myController;
	}
    
	/**
	 * Decodes the parsed tree.
	 * @param tree
	 */
	public String decode(EncodeTree tree){
		Node head = tree.getHead();
		head.evaluate();
		String result = "";
		if(head instanceof IfNode){
			return ifDecode(head);
		}
		else if(head instanceof IfElseNode){
			return ifElseDecode(head);
		}
		else if(head instanceof TurtleCommandNode){
			result = ((TurtleCommandNode) head).toString();
			//System.out.println("turtle " + result);
			return result;
		}
		else{
			result = Integer.toString(head.getValue());
			//System.out.println("other " + result);
			return result;
		}
	}
	
	/**
	 * Decodes if command.
	 * @param ifNode
	 * @return
	 */
	public String ifDecode(Node ifNode){
		((IfNode)ifNode).getChildren().get(0).evaluate();
		if(ifNode.getChildren().get(0).getValue() != 0){
			for(Node ifCommand : ifNode.getChildren().get(1).getChildren()){
				return decode(new EncodeTree(ifCommand));
			}
		}
		return "";
	}
	
	/**
	 * Decodes ifelse command.
	 * @param ifElseNode
	 * @return
	 */
	public String ifElseDecode(Node ifElseNode){
		((IfElseNode)ifElseNode).getChildren().get(0).evaluate();
		if(ifElseNode.getChildren().get(0).getValue() != 0){
			for(Node ifCommand : ifElseNode.getChildren().get(2).getChildren()){
				return decode(new EncodeTree(ifCommand));
			}
		}
		else if(ifElseNode.getChildren().get(0).getValue() == 0){
			for(Node ifCommand : ifElseNode.getChildren().get(1).getChildren()){
				return decode(new EncodeTree(ifCommand));
			}
		}
		return "";
	}
}
