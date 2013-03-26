package parser.node.logic;

import parser.node.Node;

public class AndNode extends Node {

	public AndNode() {
	}
	
	@Override
	public void setReturnValue(){
		if (getChildren().get(0).getValue() == 1 && getChildren().get(1).getValue() == 1){
			super.setValue(1);
		}else{
			super.setValue(0);
		}
	}
}
