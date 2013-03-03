package viewCommands;

import util.Turtle;
import view.DisplayView;


public class PenDown extends ViewCommand {

    public PenDown (DisplayView m) {
        myDisplayView = m;
    }

    public int executeCommand () {
        myDisplayView.getTurtle().setPenDown();
        return 1;
    }

}
