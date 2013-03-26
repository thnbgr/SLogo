package viewCommands;

import java.util.ArrayList;
import command.Command;
import util.Turtle;
import view.DisplayView;

/**
 * 
 * @author Eric Wu
 *
 */


public class SetShape extends Command {

    public SetShape () {
        myCommands = new ArrayList<String>();
        myCommands.add("setshape");
        myCommands.add("setsh");
    }

    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int shapeIndex = Integer.parseInt(args[1]);  
        myDisplayView.getTurtle().changeTurtleImage(myDisplayView.getPixmapByIndex(shapeIndex));
        return shapeIndex;
    }

}
