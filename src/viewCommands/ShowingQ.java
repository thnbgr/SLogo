package viewCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;

/**
 * 
 * @author Eric Wu
 *
 */


public class ShowingQ extends Command {

    public ShowingQ () {
        myCommands = new ArrayList<String>();
        myCommands.add("showing?");
        myCommands.add("showingp");
    }

    public int executeCommand () {
        return (myDisplayView.getTurtle().isVisible())?1:0;
    }

}