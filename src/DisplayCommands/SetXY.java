package DisplayCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;


public class SetXY extends Command {

    public SetXY (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("setxy");
    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        int r = (int) myDisplayView.getTurtle().getLocation().distance(x, y);
        myDisplayView.getTurtle().getLocation().setLocation(x, y);
        return r;
    }

}
