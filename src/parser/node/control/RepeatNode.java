package parser.node.control;

/**
 * This class deals with the repeat command
 * @author junho
 */
public class RepeatNode extends ControlNode {

    /**
     * This is the overwritten evaluate method for the repeat
     * command. 
     * the command is executed for the given number of iterations
     */
    @Override
    public void evaluate() {
        getChildren().get(0).evaluate();
        int numIterations = getChildren().get(0).getValue();
        for (int i = 0; i < numIterations; i++) {
            evaluateChildren(getChildren().get(1).getChildren());
        }
    }
}
