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

public class Model {
	private EncodeParser myParser;
	private Controller myController;
    private DecodeProcesser[] myDecodeProcessors = {new IfDecodeProcesser(this),
    		          new IfElseDecodeProcesser(this), new RepeatDecodeProcesser(this)};
    private String[] myKeywords = {"IF", "IFELSE", "REPEAT"};
	
	public Model(Controller controller){
		myParser = new EncodeParser();
	}
	
	public void setController(Controller controller){
		myController = controller;
	}
	
	public Controller getController() {
		return myController;
	}
    
    /**
     * Implements MAKE, control structures and user-defined commands, and 
     * decodes other operations.
     * @param et
     * @return
     */
	public Node decode(EncodeTree et){
		Node head = et.getHead();
		List<String> myKeywordsArrayList = Arrays.asList(myKeywords);
		if (myKeywordsArrayList.contains(head.getType())) {
			int keywordsIndex = myKeywordsArrayList.indexOf(head.getType());
			myDecodeProcessors[keywordsIndex].controlStructureProcess(head);
		}
		else{
			head.evaluate();
		}
		return head;
	}
}
