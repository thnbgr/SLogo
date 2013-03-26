package viewCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;

/**
 * 
 * @author Eric Wu
 *
 */


public class ShowTurtle extends Command {

    public ShowTurtle () {
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
