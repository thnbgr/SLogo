package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import parser.DecodeProcesser;
import parser.EncodeTree;
import parser.IfDecodeProcesser;
import parser.IfElseDecodeProcesser;
import parser.Parser;
import parser.RepeatDecodeProcesser;
import parser.node.Node;
import parser.node.turtleCommand.*;
import parser.node.control.IfNode;

public class Model {
	private Parser myParser;
	private Controller myController;
    private DecodeProcesser[] myDecodeProcessors = {new IfDecodeProcesser(this),
    		          new IfElseDecodeProcesser(this), new RepeatDecodeProcesser(this)};
    private String[] myKeywords = {"IF", "IFELSE", "REPEAT"};
	private Map<String, Double> myMakeVariables = new HashMap<String, Double>();
    
	public Model(){
		myParser = new Parser();
	}
	
	public void setController(Controller controller){
		myController = controller;
	}
	
	public Controller getController() {
		return myController;
	}
    
    /**
     * Implements control structures and user-defined commands.
     * @param et
     * @return
     */
	public Node structureDecode(EncodeTree et){ //probably don't need this.
		Node head = et.getHead();
//		List<String> myKeywordsArrayList = Arrays.asList(myKeywords);
//		int keywordsIndex = myKeywordsArrayList.indexOf(head.getType());
//		myDecodeProcessors[keywordsIndex].controlStructureProcess(head);
//		
		
		return head;
	}
	public String ifDecode(Node ifNode){
		((IfNode)ifNode).getChildren().get(0).evaluate();
		if(ifNode.getChildren().get(0).getValue() == 1){
			for(Node ifCommand : ifNode.getChildren().get(1).getChildren()){
				decode(new EncodeTree(ifCommand));
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
			
		//}
	}
}
