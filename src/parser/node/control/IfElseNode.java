package parser.node.control;

/**
 * This class is the class to store ifelse commands
 * @author junho oh 
 */
public class IfElseNode extends ControlNode {

    /**
     * This method is the overwritten evaluate method 
     * that is specific to the ifelse command. 
     * if the evaluated value of the control statement
     * is equal to zero, the second command is executed. 
     * if it is not equal to zero, the first command is
     * executed. 
     */
    @Override
    public void evaluate() {
        getChildren().get(0).evaluate();
        if (getChildren().get(0).getValue() != 0) {
            evaluateChildren(getChildren().get(1).getChildren());
        }
        else {
            evaluateChildren(getChildren().get(2).getChildren());
        }
    }
}
