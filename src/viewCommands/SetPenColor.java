package viewCommands;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import command.Command;
import view.DisplayView;

public class SetPenColor extends Command {
    
    public SetPenColor (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("setpencolor");
        myCommands.add("setpc");

    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int colorIndex = Integer.parseInt(args[1]);
        myDisplayView.getColors().setLineColorByIndex(colorIndex);
        myDisplayView.updateTurtleColors();
        return colorIndex;
    }
    
}



