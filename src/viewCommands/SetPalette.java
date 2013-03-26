package viewCommands;

import java.awt.Color;
import java.util.ArrayList;
import command.Command;
import view.DisplayView;

/**
 * 
 * @author Eric Wu
 *
 */


public class SetPalette extends Command {

    public SetPalette () {
        myCommands = new ArrayList<String>();
        myCommands.add("setpalette");
    }

    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int colorIndex = Integer.parseInt(args[1]);
        int r = Integer.parseInt(args[2]);
        int g = Integer.parseInt(args[3]);
        int b = Integer.parseInt(args[4]);
        Color myColor = new Color(r, g, b);
        myDisplayView.getColors().addColor(colorIndex, myColor);
        return 0;
    }

}
