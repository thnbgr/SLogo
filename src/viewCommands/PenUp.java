package viewCommands;

import java.util.ArrayList;
import command.Command;
import util.Turtle;
import view.DisplayView;


public class PenUp extends Command {

    public PenUp (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("penup");
        myCommands.add("pu");
    }

    public int executeCommand () {
        myDisplayView.getTurtle().setPenUp();
        return 0;
    }

}
