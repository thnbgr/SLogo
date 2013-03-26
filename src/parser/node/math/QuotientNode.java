package parser.node.math;

import parser.node.Node;

/**
 * 
 * @author Junho Oh
 */
public class QuotientNode extends Node{

	public QuotientNode() {
	}
	@Override
	public void setReturnValue(){
		if(getChildren().get(1).getValue() == 0) {
			throw new IllegalArgumentException();
		}
		else {
			setValue(getChildren().get(0).getValue() / getChildren().get(1).getValue());
		}
	}
}
