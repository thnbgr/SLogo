package viewCommands;

import java.util.ArrayList;
import command.Command;
import util.Turtle;
import view.DisplayView;

/**
 * 
 * @author Eric Wu
 *
 */

public class PenUp extends Command {

    public PenUp () {
        myCommands = new ArrayList<String>();
        myCommands.add("penup");
        myCommands.add("pu");
    }

    public int executeCommand () {
        myDisplayView.getTurtle().setPenUp();
        return 0;
    }

}
