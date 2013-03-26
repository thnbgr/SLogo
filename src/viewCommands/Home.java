package viewCommands;

import command.Command;
import java.util.ArrayList;
import util.Location;

/**
 * Returns turtle to home position 
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class Home extends Command {

    /**
     * returns turtle to home position
     * @param m is displayview
     */
    public Home () {
        myCommands = new ArrayList<String>();
        myCommands.add("home");
    }

    /**
     * executes command
     */
    public int executeCommand () {
        Location currentLocation = myDisplayView.getTurtle().getLocation();
        myDisplayView.getTurtle().moveToCenter();
        return (int) 
                currentLocation.difference(myDisplayView.getTurtle().getLocation()).getMagnitude();
    }

}
