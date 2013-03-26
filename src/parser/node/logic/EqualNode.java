package parser.node.logic;

import parser.node.Node;

public class EqualNode extends Node {

	public EqualNode() {
	}

	
	@Override
	public void setReturnValue(){
		if (getChildren().get(0).getValue() == getChildren().get(1).getValue()){
			super.setValue(1);
		}else{
			super.setValue(0);
		}
	}
}
