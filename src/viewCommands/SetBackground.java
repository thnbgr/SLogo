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

public class SetBackground extends Command {
    
    public SetBackground () {
        myCommands = new ArrayList<String>();
        myCommands.add("setbackground");
        myCommands.add("setbg");
    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int colorIndex = Integer.parseInt(args[1]);    
        Color myColor = myDisplayView.getColors().getColorByIndex(colorIndex);
        myDisplayView.getFrame().getContentPane().setBackground(myColor);
        return colorIndex;
    }
    
}



