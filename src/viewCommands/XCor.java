package viewCommands;

import util.Drawable;
import util.Sprite;
import util.Turtle;
import view.DisplayView;


public class XCor extends ViewCommand {

    public XCor (DisplayView m) {
        myDisplayView = m;
    }
    
    public void executeCommand () {
        Sprite t = (Sprite) myDisplayView.getDrawableByID(0);
        t.getLocation().getX();
    }

}

