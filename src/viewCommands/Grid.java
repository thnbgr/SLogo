package viewCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;

/**
 * 
 * @author Eric Wu
 *
 */

public class Grid extends Command {

    public Grid () {
        myCommands = new ArrayList<String>();
        myCommands.add("grid");
    }

    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int enable = Integer.parseInt(args[1]);
        myDisplayView.setGrid(enable);
        return enable;
    }

}
