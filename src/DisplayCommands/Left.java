package DisplayCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;


public class Left extends Command {

    public Left () {
        myCommands = new ArrayList<String>();
        myCommands.add("lt");
        myCommands.add("left");

    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int angle = Integer.parseInt(args[1]);
        myDisplayView.getTurtle().turn(-1 * angle);
        return angle;
    }

}
