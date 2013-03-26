package viewCommands;

import command.Command;
import java.util.ArrayList;
import view.DisplayView;

/**
 * Sets shape id based on pixmap 
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class SetShape extends Command {

    /**
     * sets the shape id
     * @param m is displayview
     */
    public SetShape (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("setshape");
        myCommands.add("setsh");
    }

    /**
     * execute command
     */
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int shapeIndex = Integer.parseInt(args[1]);  
        myDisplayView.getTurtle().changeTurtleImage(myDisplayView.getPixmapByIndex(shapeIndex));
        return shapeIndex;
    }

}
