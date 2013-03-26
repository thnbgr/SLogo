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

public class PenDownQ extends Command {

    public PenDownQ () {
        myCommands = new ArrayList<String>();
        myCommands.add("pendown?");
        myCommands.add("pendownp");
    }

    public int executeCommand () {
        return (myDisplayView.getTurtle().isPenDown())?1:0;
    }

}
