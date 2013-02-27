package viewCommands;

import util.Drawable;
import util.Turtle;
import view.DisplayView;


public class ClearScreen extends ViewCommand {

    public ClearScreen (DisplayView m) {
        myDisplayView = m;
    }
    
    public void executeCommand () {
        myDisplayView.clear();
        Turtle t = new Turtle();
        myDisplayView.addSprite(t);
    }

}

