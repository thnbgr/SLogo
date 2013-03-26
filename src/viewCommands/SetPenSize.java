package viewCommands;

import command.Command;
import java.util.ArrayList;
import view.DisplayView;

/**
 * Create clear screen 
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class SetPenSize extends Command {
    
    /**
     * sets pen size
     * @param m is displayview
     */
    public SetPenSize (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("setpensize");
        myCommands.add("setps");
    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int penSize = Integer.parseInt(args[1]);    
        myDisplayView.getTurtle().setPenSize(penSize);
        return penSize;
    }
    
}



