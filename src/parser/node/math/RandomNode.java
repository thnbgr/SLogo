package parser.node.math;

import java.util.Random;
import parser.node.Node;

public class RandomNode extends Node {

	public RandomNode() {
	}

	@Override
	public void setReturnValue(){
		Random r = new Random();
		super.setValue(r.nextInt(getChildren().get(0).getValue()));
	}
}
