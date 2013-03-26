package viewCommands;

import command.Command;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import view.DisplayView;

/**
 * sets pen color
 * @author Eric Wu
 * @author Natalia Carvalho
 */
public class PenColor extends Command {
    
    /**
     * Constructor
     * @param m dislayview
     */
    public PenColor (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("setpc");
        myCommands.add("setpencolor");
    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        String colorName = args[1];
        Color color;
        int success;
        try {
            Field field = Class.forName("java.awt.Color").getField(colorName);
            color = (Color)field.get(null);
            success = 1;
        } 
        catch (Exception e) {
            color = Color.black;
            success = 0;
        }
        myDisplayView.getColors().setLineColor(color);
        myDisplayView.updateTurtleColors();
        return success;
    }
    
}



