package parser.node.control;

public class IfNode extends ControlNode {
	
	@Override
	public void evaluate(){
		getChildren().get(0).evaluate();
		System.out.println("hi");
		if(getChildren().get(0).getValue() != 0){
			System.out.println("hi");
			evaluateChildren(getChildren().get(1).getChildren());
		}
	}
}
