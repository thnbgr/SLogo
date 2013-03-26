package parser.node.math;

import parser.node.Container;
import parser.node.Node;
/**
 * 
 * @author Junho Oh
 */
public class MinusNode extends Node {

	public MinusNode() {
	}
	
	@Override
	public void setReturnValue(){
		super.setValue(getChildren().get(0).getValue() * (-1));
	}
}
