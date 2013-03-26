package viewCommands;

import java.util.ArrayList;
import command.Command;
import util.Turtle;
import view.DisplayView;

/**
 * Pendown class
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class PenDown extends Command {
    
    /**
     * Constructor for pendown
     * @param m displayview
     */
    public PenDown (DisplayView m) {
        myDisplayView = m;
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
