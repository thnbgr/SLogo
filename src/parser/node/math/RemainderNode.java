package parser.node.math;

import parser.node.Node;
/**
 * 
 * @author Junho Oh
 */
public class RemainderNode extends Node {

	public RemainderNode() {
	}

	@Override
	public void setReturnValue(){
		super.setValue(getChildren().get(0).getValue() % getChildren().get(1).getValue());
	}
}
