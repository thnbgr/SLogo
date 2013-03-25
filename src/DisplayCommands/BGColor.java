package DisplayCommands;

import java.util.ArrayList;
import command.Command;
import view.DisplayView;

public class BGColor extends Command {
    
    public BGCOlor (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("bgcolor");
    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        int distance = Integer.parseInt(args[1]);
        myDisplayView.getTurtle().move(-1*distance);
        return distance;
    }
    
}

    frame.getContentPane().setBackground();


