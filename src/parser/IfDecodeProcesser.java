package parser;

import java.util.ArrayList;

import parser.node.Node;

import model.Model;

public class IfDecodeProcesser extends DecodeProcesser {
	private Model myModel;
	
	public IfDecodeProcesser(Model model) {
		myModel = model;
	}

	@Override
	public void controlStructureProcess(Node head) {
		int checkValue = head.getChildren().get(0).evaluate();
		if (checkValue != 0){
			Node ifCommand = new Node(head.getChilren().get(1)); //StringNode
			ArrayList<String> multipleCommands = myModel.getController().splitMultipleCommands(ifCommand.getValue());
			for (String s: multipleCommands){
				EncodeTree ifCommandET = myModel.getController().encode(s);
				myModel.decode(ifCommandET);
			}
		}

	}

}
