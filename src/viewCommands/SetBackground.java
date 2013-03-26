package viewCommands;

import command.Command;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Set background command
 * @author Eric Wu
 * @author Natalia Carvalho
 */
public class SetBackground extends Command {
    
    /**
     * set background command
     */
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



