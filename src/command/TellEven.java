package command;

import java.util.ArrayList;
import util.Turtle;
import view.DisplayView;

public class TellEven extends Command {

    
    public TellEven (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("telleven");
    }

    public int executeCommand () {
        for (int i=0;i<myDisplayView.getIDsAssigned();i+=2) {
            myDisplayView.setTurtleCommandable(i);
        }
        return myDisplayView.getIDsAssigned()-1;
    }

}
