package DisplayCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;


public class Left extends Command {

    public Left (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("lt");
    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int angle = Integer.parseInt(args[1]);
        myDisplayView.getTurtle().getVector().turn(-1 * angle);
        return angle;
    }

}
