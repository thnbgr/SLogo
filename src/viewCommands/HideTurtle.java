package viewCommands;

import view.DisplayView;


public class HideTurtle extends ViewCommand {

    public HideTurtle (DisplayView m) {
        myDisplayView = m;
    }

    public void executeCommand () {
        myDisplayView.getDrawableByID(0).setVisible(false);
    }

}
