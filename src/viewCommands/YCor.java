package viewCommands;

import java.util.ArrayList;
import command.Command;
import util.Drawable;
import util.Sprite;
import util.Turtle;
import view.DisplayView;


public class YCor extends Command {

    public YCor (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("ycor");
    }
    
    public int executeCommand () {
        return (int) myDisplayView.getTurtle().getLocation().getY();
    }

}

