package viewCommands;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import command.Command;
import view.DisplayView;

public class BGColor extends Command {
    
    public BGColor (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("bgcolor");
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
        } catch (Exception e) {
            color = Color.white;
            success = 0;
        }
        myDisplayView.getFrame().getContentPane().setBackground(color);
        return success;
    }
    
}



