package viewCommands;

import java.util.ArrayList;
import command.Command;
import util.Turtle;
import view.DisplayView;


public class PenColor extends Command {

    public PenColor (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("pencolor");
        myCommands.add("pc");
    }

    public int executeCommand () {
        return myDisplayView.getColors().getLineColorIndex();
    }

}
