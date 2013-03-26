package viewCommands;

import command.Command;
import java.util.ArrayList;

/**
 * Create pen color command
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class PenColor extends Command {

    /**
     * pen color command
     */
    public PenColor () {
        myCommands = new ArrayList<String>();
        myCommands.add("pencolor");
        myCommands.add("pc");
    }

    /**
     * executing command
     */
    public int executeCommand () {
        return myDisplayView.getColors().getLineColorIndex();
    }

}
