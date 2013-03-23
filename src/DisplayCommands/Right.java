package DisplayCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;


public class Right extends Command {

    public Right (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("rt");
        myCommands.add("right");

    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int angle = Integer.parseInt(args[1]);
        myDisplayView.getTurtle().turn(angle);
        return angle;
    }

}
