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


public class YCor extends Command {

    public YCor () {
        myCommands = new ArrayList<String>();
        myCommands.add("ycor");
    }
    
    public int executeCommand () {
        System.out.println(this.getClass());
        return (int) myDisplayView.getTurtle().getLocation().getY();
    }

}

