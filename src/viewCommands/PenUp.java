package viewCommands;

import util.Turtle;
import view.DisplayView;


public class PenUp extends ViewCommand {

    public PenUp (DisplayView m) {
        myDisplayView = m;
    }

    public void executeCommand () {
        Turtle t = (Turtle) (myDisplayView.getDrawableByID(0));
        t.penUp();
    }

}
