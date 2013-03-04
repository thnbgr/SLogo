package parser.node.math;

import parser.node.*;
/**
 * 
 * @author Junho Oh
 */
public class DifferenceNode extends Node{

	public DifferenceNode() {
	}
	
	@Override
	public void setOperation(){
		super.setValue(getChildren().get(0).getValue() - getChildren().get(1).getValue());
	}
}
