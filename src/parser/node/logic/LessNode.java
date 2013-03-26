package parser.node.logic;

import parser.node.Container;
import parser.node.Node;
/**
 * 
 * @author Junho Oh
 */
public class LessNode extends Node {

	public LessNode() {
	}

	@Override
	public void setReturnValue(){
		if (getChildren().get(0).getValue() < getChildren().get(1).getValue()){
			super.setValue(1);
		}else{
			super.setValue(0);
		}
	}
}
