package viewCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;


public class HideTurtle extends Command {

    public HideTurtle (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("hideturtle");
        myCommands.add("ht");
    }

    public int executeCommand () {
        myDisplayView.getTurtle().setVisible(false);
        return 0;
    }

}
