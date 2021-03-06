package viewCommands;

import command.Command;
import java.util.ArrayList;

/**
 * Set pen color command
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class SetPenColor extends Command {
    
    /**
     * set pen color constructor
     */
    public SetPenColor () {
        myCommands = new ArrayList<String>();
        myCommands.add("setpencolor");
        myCommands.add("setpc");

    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int colorIndex = Integer.parseInt(args[1]);
        myDisplayView.getColors().setLineColorByIndex(colorIndex);
        myDisplayView.updateTurtleColors();
        return colorIndex;
    }
    
}



