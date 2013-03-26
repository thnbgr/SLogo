package parser.node.control;

import java.util.ArrayList;
import java.util.List;

import parser.node.Node;
import parser.node.ValueNode;
import parser.node.VariableNode;

public class DoTimesNode extends ControlNode{
	
	@Override
	public void evaluate(){
		getChildren().get(1).evaluate();
		int numInterations = getChildren().get(1).getValue();
		for (int i = 0; i<numInterations; i++){
			ArrayList<Node> convertedChildren = new ArrayList<Node>();
			for (Node n: getChildren().get(2).getChildren()){
				if (n instanceof VariableNode){
					convertedChildren.add(new ValueNode(i));
				}else{
					convertedChildren.add(n);
				}
			}
			evaluateChildren(convertedChildren);
		}
	}
}
