package viewCommands;

import util.Drawable;
import util.Turtle;
import view.DisplayView;


public class ClearScreen extends ViewCommand {

    public ClearScreen (DisplayView m) {
        myDisplayView = m;
    }
    
    public int executeCommand () {
        if (myDisplayView.getTurtle() == null) return 0;
        Turtle t = new Turtle();
        int r = (int) myDisplayView.getTurtle().getLocation().difference(t.getLocation()).getMagnitude();
                
        myDisplayView.clear();
        myDisplayView.addSprite(t);
        return r;
    }

}

