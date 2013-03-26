package viewCommands;

import java.util.ArrayList;
import command.Command;
import util.Drawable;
import util.Turtle;
import view.DisplayView;

/**
 * 
 * @author Eric Wu
 *
 */

public class ClearScreen extends Command {
    
    public ClearScreen () {
        myCommands = new ArrayList<String>();
        myCommands.add("clearscreen");
        myCommands.add("cs");
    }
    
    public int executeCommand () {
        if (myDisplayView.getTurtle() == null) {
            return 0;
        }
        Turtle t = new Turtle();
        int r = (int) myDisplayView.getTurtle().getLocation().difference(t.getLocation()).getMagnitude();
                
        myDisplayView.clear();
        myDisplayView.addTurtle(t);
        return r;
    }

}

