package viewCommands;

import command.Command;
import java.util.ArrayList;
import view.DisplayView;

/**
 * Create pen color command
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class PenColor extends Command {

    /**
     * pen color command
     * @param m is displayview
     */
    public PenColor (DisplayView m) {
        myDisplayView = m;
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
