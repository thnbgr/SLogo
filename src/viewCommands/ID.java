package viewCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;

/**
 * 
 * @author Eric Wu
 *
 */


public class ID extends Command {

    public ID () {
        myCommands = new ArrayList<String>();
        myCommands.add("id");
    }

    public int executeCommand () {
        return myDisplayView.getTurtle().getID();
    }

}
