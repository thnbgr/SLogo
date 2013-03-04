package DisplayCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;

public class Towards extends Command {
    
    public Towards (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("towards");
    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        int angle = (int) myDisplayView.getTurtle().getVector().angleBetween(x, y);
        myDisplayView.getTurtle().getVector().turn(angle);
        return angle;
    }
    
}
