package parser;

import java.util.ArrayList;

import parser.node.Node;

import model.Model;

public class RepeatDecodeProcesser extends DecodeProcesser {
	private Model myModel;

	public RepeatDecodeProcesser(Model model) {
		myModel = model;
	}

	@Override
	public void controlStructureProcess(Node head) {
		int repeatValue = head.getChildren().get(0).evaluate();
		Node repeatCommand = new Node(head.getChilren().get(1));
		ArrayList<String> multipleCommands = myModel.getController().splitMultipleCommands(repeatCommand.getValue());
		for (int i = 0; i<repeatValue; ++i){
			for (String s: multipleCommands){
				EncodeTree ifCommandET = myModel.getController().encode(s);
				myModel.decode(ifCommandET);
			}
		}
	}
}