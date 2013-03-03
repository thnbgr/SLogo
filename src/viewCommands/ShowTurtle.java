package viewCommands;

import view.DisplayView;


public class ShowTurtle extends ViewCommand {

    public ShowTurtle (DisplayView m) {
        myDisplayView = m;
    }

    public int executeCommand () {
        myDisplayView.getTurtle().setVisible(true);
        return 1;
    }

}
