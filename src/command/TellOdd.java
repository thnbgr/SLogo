package command;

import java.util.ArrayList;
import view.DisplayView;

public class TellOdd extends Command {

    public TellOdd (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("tellodd");
    }

    
    @Override
    public int executeCommand () {
        for (int i=1;i<myDisplayView.getIDsAssigned();i+=2) {
            myDisplayView.setTurtleCommandable(i);
        }
        return myDisplayView.getIDsAssigned()-1;
    }

}
