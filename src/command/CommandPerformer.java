package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import view.DisplayView;
import viewCommands.*;


/**
 * 
 * This makes all changes necessary to DisplayView
 * performs the processed, simplified command
 * 
 * @author Eric Wu
 * 
 */
public class CommandPerformer extends Observable {

    private DisplayView myDisplayView;
    private List<Command> myDisplayCommands;
    private CommandBuilder myCommandBuilder;

    public CommandPerformer (DisplayView d, CommandBuilder c) {
        myDisplayView = d;
        myDisplayCommands = new ArrayList<Command>();
        myCommandBuilder = c;
        addDisplayCommands();
    }

    public void addDisplayCommands () {
        myDisplayCommands = myCommandBuilder.populateDisplayCommandsList();
    }
    

    public int sendAction (String input) {

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
