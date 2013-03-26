package viewCommands;

import command.Command;
import java.util.ArrayList;
import view.DisplayView;

/**
 * creates a stamp of current turtle
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class Stamp extends Command {

    /**
     * stamp constructor
     * @param m displayview
     */
    public Stamp (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("stamp");
    }
    
    /**
     * execute command
     */
    public int executeCommand () {
        System.out.println(myDisplayView.myStamps.size());
        myDisplayView.createStamp(myDisplayView.getTurtle());
        return 0;
    }

}
