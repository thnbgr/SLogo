package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import parser.DecodeProcesser;
import parser.EncodeTree;
import parser.EncodeParser;
import parser.IfDecodeProcesser;
import parser.IfElseDecodeProcesser;
import parser.RepeatDecodeProcesser;
import parser.node.Node;
import parser.node.turtleCommand.*;

public class Model {
	private EncodeParser myParser;
	private Controller myController;
    private DecodeProcesser[] myDecodeProcessors = {new IfDecodeProcesser(this),
    		          new IfElseDecodeProcesser(this), new RepeatDecodeProcesser(this)};
    private String[] myKeywords = {"IF", "IFELSE", "REPEAT"};
	private Map<String, Double> myMakeVariables = new HashMap<String, Double>();
    
	public Model(){
		myParser = new EncodeParser();
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
	//public Node structureDecode(EncodeTree et){ //probably don't need this.
		//Node head = et.getHead();
		//List<String> myKeywordsArrayList = Arrays.asList(myKeywords);
		//int keywordsIndex = myKeywordsArrayList.indexOf(head.getType());
		//myDecodeProcessors[keywordsIndex].controlStructureProcess(head);
		
		//return head;
	//}
	
	public String decode(EncodeTree et){
		Node head = et.getHead();
		head.evaluate();
		//if (head instanceof TurtleCommandNode){
		return ((TurtleCommandNode) head).toString();
			
		//}
	}
}
