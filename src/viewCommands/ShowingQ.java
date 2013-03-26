package viewCommands;

import command.Command;
import java.util.ArrayList;
import view.DisplayView;

/**
 * returns whether turtle is visible or not
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class ShowingQ extends Command {

    /**
     * showing turtle? constructor 
     * @param m is displayview
     */
    public ShowingQ (DisplayView m) {
        myDisplayView = m;
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