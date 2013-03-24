package model;

import java.util.HashMap;
import java.util.Map;

import controller.ModelController;
import parser.EncodeTree;
import parser.TreeMakingParser;
import parser.node.Node;
import parser.node.turtleCommand.*;
import parser.node.control.IfNode;
import view.Workspace;

/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class Model {
	private TreeMakingParser myParser;
	private ModelController myController;
	private Map<String, Double> myMakeVariables = new HashMap<String, Double>();
	
	public void setController(ModelController controller){
		myController = controller;
	}
	
	public void setParser(TreeMakingParser t){
		myParser = t;
	}
	
	public ModelController getController() {
		return myController;
	}
    
	public String ifDecode(Node ifNode){
		((IfNode)ifNode).getChildren().get(0).evaluate();
		if(ifNode.getChildren().get(0).getValue() == 1){
			for(Node ifCommand : ifNode.getChildren().get(1).getChildren()){
				decode(new EncodeTree(ifCommand)); // TODO: add workspace
			}
		}
		return new String();
	}
	public String decode(EncodeTree et){
		Node head = et.getHead();
		if(head instanceof IfNode){
			ifDecode(head);
		}
		else{
			head.evaluate();
		}
		return ((TurtleCommandNode) head).toString(); 
	}
}
