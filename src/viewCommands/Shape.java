package viewCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;


public class Shape extends Command {

    public Shape (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("shape");
        myCommands.add("sh");
    }

    @Override
    public int executeCommand () {
        return myDisplayView.getIndexByPixmap((myDisplayView.getTurtle().getImage()));
    }

}
