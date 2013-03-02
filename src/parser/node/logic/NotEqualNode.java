package parser.node.logic;

import parser.node.Container;
import parser.node.Node;

public class NotEqualNode extends LogicNode {

	public NotEqualNode() {
	}

	@Override
	public void setContainerValue(Container<Boolean> container) {
		container.setValue(((Container<Double>)getLeft().getContainer()).getValue() != ((Container<Double>)getRight().getContainer()).getValue());
	}

}
