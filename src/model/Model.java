package model;

import java.util.HashMap;
import java.util.Map;

import controller.ModelController;
import parser.CommandTreeParser;
import parser.node.Node;
import parser.node.turtleCommand.*;
import parser.node.control.IfElseNode;

/**
 *
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class Model {
	private ModelController myController;

	/**
	 * Bonds the given controller to model.
	 * @param controller the Controller to be set.
	 */
	public void setController(ModelController controller) {
		myController = controller;
	}

	/**
	 * Gets the controller.
	 * @return the controller
	 */
	public ModelController getController() {
		return myController;
	}

	/**
	 * Decodes the parsed tree.
	 * @param head The head of the tree.
	 * @return The decoded String of turtle command or execution value.
	 */
	public String decode (Node head) {
		head.evaluate();
		String result = "";
		if (head instanceof IfElseNode) {
			return ifElseDecode(head);
		}
		else if (head instanceof TurtleCommandNode) {
			result = ((TurtleCommandNode) head).toString();
			return result;
		}
		else {
			result = Integer.toString(head.getValue());
			return result;
		}
	}

	/**
	 * Decodes ifelse command.
	 * @param ifElseNode
	 * @return
	 */
	public String ifElseDecode (Node ifElseNode) {
		((IfElseNode)ifElseNode).getChildren().get(0).evaluate();
		if (ifElseNode.getChildren().get(0).getValue() != 0) {
			for (Node ifCommand : ifElseNode.getChildren().get(2).getChildren()) {
				return decode(ifCommand);
			}
		}
		else if(ifElseNode.getChildren().get(0).getValue() == 0){
			for (Node ifCommand : ifElseNode.getChildren().get(1).getChildren()){
				return decode(ifCommand);
			}
		}
		return "";
	}
}
