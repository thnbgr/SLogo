package command;

import java.util.ArrayList;
import view.DisplayView;

public class Tell extends Command {

    public Tell (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("tell");
    }

    @Override
    public int executeCommand () {
        String[] args = myCommandString.split(" ");
        for (int i = 1; i < args.length;i++) {
            int id = Integer.parseInt(args[i]);  
            myDisplayView.setTurtleCommandable(id);
        }
        return 0;
    }

}
