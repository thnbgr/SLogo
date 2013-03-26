package viewCommands;

import command.Command;
import java.util.ArrayList;

/**
 * return y coordinate of turtle command
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class YCor extends Command {

    /**
     * y coordinate constructor
     */
    public YCor () {
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

