package DisplayCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;


public class SetXY extends Command {

    public SetXY () {
        myCommands = new ArrayList<String>();
        myCommands.add("setxy");
        myCommands.add("goto");

    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        int r = (int) myDisplayView.getTurtle().getLocation().distance(x, y);
        myDisplayView.getTurtle().getLocation().setLocation(x, y);
        myDisplayView.getTurtle().clearLines();
        return r;
    }

}
