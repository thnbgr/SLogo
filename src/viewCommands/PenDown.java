package viewCommands;

import java.util.ArrayList;
import command.Command;
import util.Turtle;
import view.DisplayView;


public class PenDown extends Command {

    public PenDown (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("pendown");
        myCommands.add("pd");
    }

    public int executeCommand () {
        myDisplayView.getTurtle().setPenDown();
        return 1;
    }

}
