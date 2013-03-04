package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parser.node.Node;

import model.Model;

public class MakeDecodeProcesser extends DecodeProcesser {
	private Model myModel;
	private Map<String, Integer> myMakeVariables = new HashMap<String, Integer>();
	
	public MakeDecodeProcesser(Model model) {
		myModel = model;
	}

	@Override
	public void controlStructureProcess(Node head) {
		String variableName = head.getChildren().get(0).getValue();
		Node makeValueCommand = new Node(head.getChilren().get(1));
		makeValueCommand.evaluate();
		
		
		
		
		if (checkValue != 0){
			Node ifElseCommand = new Node(head.getChilren().get(1)); //StringNode
			ArrayList<String> multipleCommands = myModel.getController().splitMultipleCommands(ifElseCommand.getValue());
			for (String s: multipleCommands){
				EncodeTree ifElseCommandET = myModel.getController().encode(s);
				myModel.decode(ifElseCommandET);
			}
		}else{
			Node ifElseCommand = new Node(head.getChilren().get(2)); //StringNode
			ArrayList<String> multipleCommands = myModel.getController().splitMultipleCommands(ifElseCommand.getValue());
			for (String s: multipleCommands){
				EncodeTree ifElseCommandET = myModel.getController().encode(s);
				myModel.decode(ifElseCommandET);
			}
		}
	}
}