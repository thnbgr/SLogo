package viewCommands;

import util.Drawable;
import util.Sprite;
import util.Turtle;
import view.DisplayView;


public class XCor extends ViewCommand {

    public XCor (DisplayView m) {
        myDisplayView = m;
    }
    
    public int executeCommand () {
        return (int) myDisplayView.getTurtle().getLocation().getX();
    }

}

