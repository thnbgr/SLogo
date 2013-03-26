package model;

import java.util.HashMap;
import java.util.Map;

import controller.ModelController;
import parser.EncodeTree;
import parser.CommandTreeParser;
import parser.node.Node;
import parser.node.turtleCommand.*;
import parser.node.control.IfElseNode;
import parser.node.control.IfNode;
import view.Workspace;

/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class Model {
	private CommandTreeParser myParser;
	private ModelController myController;
	private Map<String, Double> myMakeVariables = new HashMap<String, Double>();
	
	public void setController(ModelController controller){
		myController = controller;
	}
	
	public void setParser(CommandTreeParser t){
		myParser = t;
	}
	
	public ModelController getController() {
		return myController;
	}
    
	/**
	 * Decodes the parsed tree.
	 * @param tree
	 */
	public String decode(Node head){
		head.evaluate();
		String result = "";
		if(head instanceof IfElseNode){
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
	 * Decodes ifelse command.
	 * @param ifElseNode
	 * @return
	 */
	public String ifElseDecode(Node ifElseNode){
		((IfElseNode)ifElseNode).getChildren().get(0).evaluate();
		if(ifElseNode.getChildren().get(0).getValue() != 0){
			for(Node ifCommand : ifElseNode.getChildren().get(2).getChildren()){
				return decode(ifCommand);
			}
		}
		else if(ifElseNode.getChildren().get(0).getValue() == 0){
			for(Node ifCommand : ifElseNode.getChildren().get(1).getChildren()){
				return decode(ifCommand);
			}
		}
		return "";
	}
}
