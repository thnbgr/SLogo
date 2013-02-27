package viewCommands;

import util.Turtle;
import view.DisplayView;


public class PenDown extends ViewCommand {

    public PenDown (DisplayView m) {
        myDisplayView = m;
    }

    public void executeCommand () {
        Turtle t = (Turtle) (myDisplayView.getDrawableByID(0));
        t.penDown();
    }

}
