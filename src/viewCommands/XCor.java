package viewCommands;

import java.util.ArrayList;
import command.Command;
import util.Drawable;
import util.Sprite;
import util.Turtle;
import view.DisplayView;


public class XCor extends Command {

    public XCor (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("xcor");
    }
    
    public int executeCommand () {
        return (int) myDisplayView.getTurtle().getLocation().getX();
    }

}

