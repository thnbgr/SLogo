package parser.node.logic;

import parser.node.Container;
import parser.node.Node;

public class NotNode extends Node {

	public NotNode() {
	}

	
	@Override
	public void setReturnValue(){
		if (getChildren().get(0).getValue() == 0){
			super.setValue(1);
		}else{
			super.setValue(0);
		}
	}

}
