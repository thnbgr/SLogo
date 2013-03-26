package DisplayCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;

public class Forward extends Command {
    
    public Forward () {
        myCommands = new ArrayList<String>();
        myCommands.add("fd");
        myCommands.add("forward");
    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int distance = Integer.parseInt(args[1]);
        myDisplayView.getTurtle().move(distance);
        return distance;
    }
    
}
