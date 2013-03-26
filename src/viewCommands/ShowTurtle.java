package viewCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;


public class ShowTurtle extends Command {

    public ShowTurtle (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("showturtle");
        myCommands.add("st");
    }

    public int executeCommand () {
        System.out.println("show turtle");
        myDisplayView.getTurtle().setVisible(true);
        return 1;
    }

}
