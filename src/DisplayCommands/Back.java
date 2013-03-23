package DisplayCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;

public class Back extends Command {
    
    public Back (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("bk");
        myCommands.add("back");

    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int distance = Integer.parseInt(args[1]);
        myDisplayView.getTurtle().move(-1*distance);
        return distance;
    }
    
}
