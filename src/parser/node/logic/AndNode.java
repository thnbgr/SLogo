package parser.node.logic;

import parser.node.Container;
import parser.node.Node;

public class AndNode extends LogicNode {

	public AndNode() {
	}

	@Override
	public void setContainerValue(Container<Boolean> container) {
		container.setValue(((Container<Boolean>)getLeft().getContainer()).getValue() && ((Container<Boolean>)getRight().getContainer()).getValue());
	}

}
