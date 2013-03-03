package viewCommands;

import view.DisplayView;


public class HideTurtle extends ViewCommand {

    public HideTurtle (DisplayView m) {
        myDisplayView = m;
    }

    public int executeCommand () {
        myDisplayView.getTurtle().setVisible(false);
        return 0;
    }

}
