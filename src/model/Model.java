package model;

import java.util.HashMap;
import java.util.Map;

import controller.ModelController;
import parser.EncodeTree;
import parser.Parser;
import parser.node.Node;
import parser.node.turtleCommand.*;
import parser.node.control.IfNode;

/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class Model {
	private Parser myParser;
	private ModelController myController;
	private Map<String, Double> myMakeVariables = new HashMap<String, Double>();
    
	public Model(){
		myParser = new Parser("");
	}
	
	public void setController(ModelController controller){
		myController = controller;
	}
	
	public ModelController getController() {
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
			System.out.println("hi");
			ifDecode(head);
		}
		else{
			head.evaluate();
			return ((TurtleCommandNode) head).toString();
		}
		return "";
			
		//}
	}
}
