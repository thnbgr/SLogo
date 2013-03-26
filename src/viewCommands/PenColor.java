package viewCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;


/**
 * 
 * @author Eric Wu
 *
 */

public class PenColor extends Command {

    public PenColor () {
        myCommands = new ArrayList<String>();
        myCommands.add("pencolor");
        myCommands.add("pc");
    }

    public int executeCommand () {
        return myDisplayView.getColors().getLineColorIndex();
    }

}
