package viewCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;

/**
 * 
 * @author Eric Wu
 *
 */


public class Stamp extends Command {

    public Stamp () {
        myCommands = new ArrayList<String>();
        myCommands.add("stamp");
    }

    public int executeCommand () {
        myDisplayView.createStamp(myDisplayView.getTurtle());
        return 0;
    }

}
