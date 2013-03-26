package viewCommands;

import command.Command;
import java.util.ArrayList;

/**
 * Creates is pen down? command
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class PenDownQ extends Command {

    /**
     * constructor for is pen down command
     */
    public PenDownQ () {
        myCommands = new ArrayList<String>();
        myCommands.add("pendown?");
        myCommands.add("pendownp");
    }

    /**
     * executes is pen down command
     */
    public int executeCommand () {
        return (myDisplayView.getTurtle().isPenDown()) ? 1 : 0;
    }

}
