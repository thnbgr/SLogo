package viewCommands;

import java.util.ArrayList;
import command.Command;
import util.Turtle;
import view.DisplayView;


public class PenDownQ extends Command {

    public PenDownQ (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("pendown?");
        myCommands.add("pendownp");
    }

    public int executeCommand () {
        return (myDisplayView.getTurtle().isPenDown())?1:0;
    }

}
