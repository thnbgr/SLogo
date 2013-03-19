package command;

import java.util.List;
import view.DisplayView;


public abstract class Command {
    protected DisplayView myDisplayView;
    protected List<String> myCommands;
    protected String myCommandString;

    public abstract int executeCommand ();
    
    public void addCommandName (String s) {
        myCommands.add(s);
    }
    
    public void addCommandString (String s) {
        myCommandString = s;
    }

    public boolean hasCommand (String s) {
        return myCommands.contains(s);
    }

    public List<String> getCommands () {
        return myCommands;
    }

}
