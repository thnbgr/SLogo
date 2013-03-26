package parser.node.control;

import java.util.ArrayList;

import parser.node.Node;
import parser.node.ValueNode;
import parser.node.VariableNode;

public class ForNode extends ControlNode{
	
	@Override
	public void evaluate(){
		getChildren().get(1).evaluate();
		int startInt = getChildren().get(1).getValue();
		getChildren().get(2).evaluate();
		int endInt = getChildren().get(2).getValue();
		getChildren().get(3).evaluate();
		int increaseInt = getChildren().get(3).getValue();
		while(startInt < endInt){
			ArrayList<Node> convertedChildren = new ArrayList<Node>();
			for (Node n: getChildren().get(2).getChildren()){
				if (n instanceof VariableNode){
					convertedChildren.add(new ValueNode(startInt));
				}else{
					convertedChildren.add(n);
				}
			}
			evaluateChildren(convertedChildren);
			startInt += increaseInt;
		}
	}
}
