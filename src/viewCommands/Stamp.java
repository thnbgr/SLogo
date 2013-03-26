package viewCommands;

import command.Command;
import java.util.ArrayList;

/**
 * creates a stamp of current turtle
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class Stamp extends Command {

    /**
     * stamp constructor
     */
    public Stamp () {
        myCommands = new ArrayList<String>();
        myCommands.add("stamp");
    }
    
    /**
     * execute command
     */
    public int executeCommand () {
        myDisplayView.createStamp(myDisplayView.getTurtle());
        return 0;
    }

}
