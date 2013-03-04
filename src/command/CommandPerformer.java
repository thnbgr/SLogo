package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import view.DisplayView;
import view.IView;
import viewCommands.*;


/**
 * 
 * This makes all changes necessary to DisplayView
 * 
 * @author Eric Wu
 * 
 */
public class CommandPerformer extends Observable {

    private DisplayView myDisplayView;
    // private CommandBundle myCommandBundle;
    private List<Command> myDisplayCommands;

    public CommandPerformer (IView d) {
        myDisplayView = (DisplayView) d;
        myDisplayCommands = new ArrayList<Command>();
        addDisplayCommands();
    }

    public void addDisplayCommands () {
        CommandBuilder builder = new CommandBuilder(myDisplayView);
        myDisplayCommands = builder.populateDisplayCommandsList();
    }
    

    public int sendAction (String input) { // fd 50

        for (Command v : myDisplayCommands) {
            for (String s : v.getCommands()) {
                if (input.contains(s)) {
                    v.addCommandString(input);
                    int r = v.executeCommand();
                    return r;
                }
            }
        }
        return 0;
    }

}
