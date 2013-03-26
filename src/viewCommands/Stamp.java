package viewCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;


public class Stamp extends Command {

    public Stamp (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("stamp");
    }

    public int executeCommand () {
        System.out.println(myDisplayView.myStamps.size());
        myDisplayView.createStamp(myDisplayView.getTurtle());
        return 0;
    }

}
