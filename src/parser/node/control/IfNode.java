package parser.node.control;

import parser.node.Node;

public class IfNode extends ControlNode {
	
	@Override
	public void evaluate(){
		getChildren().get(0).evaluate();
		if(getChildren().get(0).getValue() != 0){
			for(Node ifCommand : getChildren().get(1).getChildren()){
				ifCommand.evaluate();
				addReturnCommand(ifCommand);
				System.out.println(ifCommand.getValue());
			}
			int childSize = getChildren().get(1).getChildren().size();
			setValue(getChildren().get(1).getChildren().get(childSize-1).getValue());
		}
	}
}
