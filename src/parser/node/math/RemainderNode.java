package parser.node.math;

import parser.node.Container;
import parser.node.Node;
/**
 * 
 * @author Junho Oh
 */
public class RemainderNode extends Node {
	//should be extension of quotient? 
	public RemainderNode() {
	}

	@Override
	public void setValue(){
		super.setValue(getChildren().get(0).getValue() % getChildren().get(1).getValue());
	}
}
