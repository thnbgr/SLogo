package viewCommands;

import util.Turtle;
import view.DisplayView;


public class PenUp extends ViewCommand {

    public PenUp (DisplayView m) {
        myDisplayView = m;
    }

    public int executeCommand () {
        myDisplayView.getTurtle().setPenUp();
        return 0;
    }
}
