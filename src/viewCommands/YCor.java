package viewCommands;

import command.Command;
import java.util.ArrayList;
import view.DisplayView;

/**
 * return y coordinate of turtle command
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class YCor extends Command {

    /**
     * y coordinate constructor
     * @param m displayview
     */
    public YCor (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("ycor");
    }
    
    /**
     * execute command
     */
    public int executeCommand () {
        return (int) myDisplayView.getTurtle().getLocation().getY();
    }

}

