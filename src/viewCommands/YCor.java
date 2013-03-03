package viewCommands;

import util.Drawable;
import util.Sprite;
import util.Turtle;
import view.DisplayView;


public class YCor extends ViewCommand {

    public YCor (DisplayView m) {
        myDisplayView = m;
    }
    
    public int executeCommand () {
        return (int) myDisplayView.getTurtle().getLocation().getY();
    }

}

