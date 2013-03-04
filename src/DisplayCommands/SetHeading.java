package DisplayCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;

public class SetHeading extends Command {
    
    public SetHeading (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("seth");
    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int angle = Integer.parseInt(args[1]);
        int originalAngle = (int) (myDisplayView.getTurtle().getVector().getDirection() - angle);
        myDisplayView.getTurtle().getVector().setDirection(angle);
        return originalAngle;
    }
    
}
