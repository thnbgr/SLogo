package viewCommands;

import command.Command;
import java.util.ArrayList;

/**
 * returns x coordinate of turtle
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class XCor extends Command {

    /**
     * x coordinate constructor
     */
    public XCor () {
        myCommands = new ArrayList<String>();
        myCommands.add("xcor");
    }
    
    /**
     * execute command
     */
    public int executeCommand () {
        return (int) myDisplayView.getTurtle().getLocation().getX();
    }

}

