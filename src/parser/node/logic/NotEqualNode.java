package parser.node.logic;

import parser.node.Container;
import parser.node.Node;

public class NotEqualNode extends Node {

	public NotEqualNode() {
	}

	
	@Override
	public void setOperation(){
		if (getChildren().get(0).getValue() != getChildren().get(1).getValue()){
			super.setValue(1);
		}else{
			super.setValue(0);
		}
	}

}
