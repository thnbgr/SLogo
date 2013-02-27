package viewCommands;

import view.DisplayView;


public class ShowTurtle extends ViewCommand {

    public ShowTurtle (DisplayView m) {
        myDisplayView = m;
    }

    public void executeCommand () {
        myDisplayView.getDrawableByID(0).setVisible(true);
    }

}
