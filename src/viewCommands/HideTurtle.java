package viewCommands;

import command.Command;
import java.util.ArrayList;
import view.DisplayView;

/**
 * Hide turtle from screen
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class HideTurtle extends Command {

    /**
     * hide turtle command
     * @param m is displayview
     */
    public HideTurtle (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("hideturtle");
        myCommands.add("ht");
    }

    /**
     * executing command
     */
    public int executeCommand () {
        myDisplayView.getTurtle().setVisible(false);
        return 0;
    }

}
