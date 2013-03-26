package viewCommands;

import java.util.ArrayList;
import command.Command;
import util.Drawable;
import util.Sprite;
import util.Turtle;
import view.DisplayView;

/**
 * 
 * @author Eric Wu
 *
 */


public class XCor extends Command {

    public XCor () {
        myCommands = new ArrayList<String>();
        myCommands.add("xcor");
    }
    
    public int executeCommand () {
        return (int) myDisplayView.getTurtle().getLocation().getX();
    }

}

