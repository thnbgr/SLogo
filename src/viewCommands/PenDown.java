package viewCommands;

import command.Command;
import java.util.ArrayList;

/**
 * Pendown class
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class PenDown extends Command {
    
    /**
     * Constructor for pendown
     */
    public PenDown () {
        myCommands = new ArrayList<String>();
        myCommands.add("pendown");
        myCommands.add("pd");
    }

    /**
     * Executes command
     */
    public int executeCommand () {
        myDisplayView.getTurtle().setPenDown();
        return 1;
    }

}
