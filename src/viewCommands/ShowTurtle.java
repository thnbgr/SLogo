package viewCommands;

import command.Command;
import java.util.ArrayList;

/**
 * show turtle command
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class ShowTurtle extends Command {

    /**
     * showing turtle constructor
     */
    public ShowTurtle () {
        myCommands = new ArrayList<String>();
        myCommands.add("showturtle");
        myCommands.add("st");
    }

    /**
     * execute command
     */
    public int executeCommand () {
        System.out.println("show turtle");
        myDisplayView.getTurtle().setVisible(true);
        return 1;
    }

}
