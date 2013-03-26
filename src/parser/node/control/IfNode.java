package parser.node.control;

/**
 * This class is the class that deals with if commands
 * @author junho oh
 *
 */
public class IfNode extends ControlNode {

    /**
     * This class is the overwritten evaluate method specific
     * to the if command. 
     * if the control statement is not equal to zero, the commands
     * are executed
     */
    @Override
    public void evaluate() {
        getChildren().get(0).evaluate();
        if (getChildren().get(0).getValue() != 0) {
            evaluateChildren(getChildren().get(1).getChildren());
        }
    }
}
