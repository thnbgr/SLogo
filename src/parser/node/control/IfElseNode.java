package parser.node.control;

public class IfElseNode extends ControlNode {

	@Override
	public void evaluate(){
		getChildren().get(0).evaluate();
		if(getChildren().get(0).getValue() != 0){
			evaluateChildren(getChildren().get(1).getChildren());
		}
		else{
			evaluateChildren(getChildren().get(2).getChildren());
		}
	}
}
