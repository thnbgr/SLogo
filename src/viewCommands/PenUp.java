package viewCommands;

import command.Command;
import java.util.ArrayList;
import view.DisplayView;

/**
 * changes whether pen is up
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class PenUp extends Command {
    
    /**
     * pen up command
     * @param m is displayview
     */
    public PenUp (DisplayView m) {
        myDisplayView = m;
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
