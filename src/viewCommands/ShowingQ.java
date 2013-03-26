package viewCommands;

import command.Command;
import java.util.ArrayList;

/**
 * returns whether turtle is visible or not
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class ShowingQ extends Command {

    /**
     * showing turtle? constructor 
     */
    public ShowingQ () {
        myCommands = new ArrayList<String>();
        myCommands.add("showing?");
        myCommands.add("showingp");
    }

    /**
     * execute command
     */
    public int executeCommand () {
        return (myDisplayView.getTurtle().isVisible()) ? 1 : 0;
    }

}