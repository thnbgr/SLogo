package parser;

import java.util.ArrayList;

import parser.node.Node;

import model.Model;

public class IfElseDecodeProcesser extends DecodeProcesser {
	private Model myModel;
	
	public IfElseDecodeProcesser(Model model) {
		myModel = model;
	}

	@Override
	public void controlStructureProcess(Node head) {
		int checkValue = head.getChildren().get(0).evaluate();
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
