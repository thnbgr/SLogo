package viewCommands;

import command.Command;
import java.util.ArrayList;

/**
 * changes whether pen is up
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class PenUp extends Command {
    
    /**
     * pen up command
     */
    public PenUp () {
        myCommands = new ArrayList<String>();
        myCommands.add("penup");
        myCommands.add("pu");
    }

    /**
     * executes command
     */
    public int executeCommand () {
        myDisplayView.getTurtle().setPenUp();
        return 0;
    }

}
