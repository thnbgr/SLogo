package viewCommands;

import command.Command;
import java.util.ArrayList;

/**
 * Clear all stamps 
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class ClearStamps extends Command {

    /**
     * Constructor to clear stamps
     */
    public ClearStamps () {
        myCommands = new ArrayList<String>();
        myCommands.add("clearstamps");
    }

    /**
     * Method that clears all stamps
     */
    public int executeCommand () {
        myDisplayView.clearStamps();
        return 0;
    }

}
