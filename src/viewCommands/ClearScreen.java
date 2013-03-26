package viewCommands;

import command.Command;
import java.util.ArrayList;
import util.Turtle;
import view.DisplayView;


/**
 * Create clear screen 
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class ClearScreen extends Command {

    /**
     * clear screen command
     * @param m is displayview
     */
    public ClearScreen (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("clearscreen");
        myCommands.add("cs");
    }
    
    /**
     * executing command
     */
    public int executeCommand () {
        if (myDisplayView.getTurtle() == null) {
            return 0;
        }
        Turtle t = new Turtle();
        int r = (int) 
                myDisplayView.getTurtle().getLocation().difference(t.getLocation()).getMagnitude();
                
        myDisplayView.clear();
        myDisplayView.addTurtle(t);
        return r;
    }

}

