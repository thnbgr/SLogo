package viewCommands;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import command.Command;
import view.DisplayView;


/**
 * 
 * @author Eric Wu
 *
 */


public class SetPenSize extends Command {
    
    public SetPenSize () {
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



