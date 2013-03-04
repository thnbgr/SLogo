package parser.node.math;

import parser.node.*;
/**
 * 
 * @author Junho Oh
 */
public class SumNode extends Node{
	public SumNode() {
	}
	@Override
	public void setOperation(){
		super.setValue(getChildren().get(0).getValue() + getChildren().get(1).getValue());
	}
}
