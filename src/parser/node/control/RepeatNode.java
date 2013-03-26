package parser.node.control;

public class RepeatNode extends ControlNode {

	@Override
	public void evaluate(){
		getChildren().get(0).evaluate();
		int numIterations = getChildren().get(0).getValue();
		for(int i = 0; i < numIterations; i++){
			evaluateChildren(getChildren().get(1).getChildren());
		}
	}
}
