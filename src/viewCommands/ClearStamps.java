package viewCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;

/**
 * 
 * @author Eric Wu
 *
 */

public class ClearStamps extends Command {

    public ClearStamps () {
        myCommands = new ArrayList<String>();
        myCommands.add("clearstamps");
    }

    public int executeCommand () {
        myDisplayView.clearStamps();
        return 0;
    }

}
