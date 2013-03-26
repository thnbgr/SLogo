package viewCommands;

import java.util.ArrayList;
import command.Command;
import util.Location;
import util.Turtle;
import view.DisplayView;


/**
 * 
 * @author Eric Wu
 *
 */

public class Home extends Command {

    public Home () {
        myCommands = new ArrayList<String>();
        myCommands.add("home");
    }

    public int executeCommand () {
        Location currentLocation = myDisplayView.getTurtle().getLocation();
        myDisplayView.getTurtle().moveToCenter();
        return (int) currentLocation.difference(myDisplayView.getTurtle().getLocation()).getMagnitude();
    }

}
