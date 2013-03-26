package viewCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;


public class ClearStamps extends Command {

    public ClearStamps (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("clearstamps");
    }

    public int executeCommand () {
        myDisplayView.clearStamps();
        return 0;
    }

}
