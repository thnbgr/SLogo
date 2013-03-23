package DisplayCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;

public class SetHeading extends Command {
    
    public SetHeading (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("seth");
        myCommands.add("setheading");
    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int angle = Integer.parseInt(args[1]);
        int originalAngle = (int) (angle - myDisplayView.getTurtle().getVector().getDirection());
        myDisplayView.getTurtle().getVector().setDirection(angle);
        return originalAngle;
    }
    
}
