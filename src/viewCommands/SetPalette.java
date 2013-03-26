package viewCommands;

import command.Command;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Set palette command
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class SetPalette extends Command {

    private static final int R_VALUE = 2;
    private static final int G_VALUE = 3;
    private static final int B_VALUE = 4;

    /**
     * set palette command
     */
    public SetPalette () {
        myCommands = new ArrayList<String>();
        myCommands.add("setpalette");
    }

    /**
     * executing command
     */
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        if (args.length < 5) return -1;
        int colorIndex = Integer.parseInt(args[1]);
        int r = Integer.parseInt(args[R_VALUE]);
        int g = Integer.parseInt(args[G_VALUE]);
        int b = Integer.parseInt(args[B_VALUE]);
        Color myColor = new Color(r, g, b);
        myDisplayView.getColors().addColor(colorIndex, myColor);
        return 0;
    }

}
