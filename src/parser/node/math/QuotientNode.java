package parser.node.math;

import parser.node.Container;
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
		super.setValue(getChildren().get(0).getValue() / getChildren().get(1).getValue());
	}
}
