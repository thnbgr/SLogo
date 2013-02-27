package command;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import util.Drawable;
import util.Processable;
import view.CommandViewBuilder;
import view.DisplayView;
import view.IView;
import viewCommands.*;


public class CommandParser extends Observable {

    private String myStringCommand;
    private Processable myProcessable;
    private DisplayView myDisplayView;
    private CommandBundle myCommandBundle;
    private List<ViewCommand> myViewCommands;

    public CommandParser (IView d) {
        myDisplayView = (DisplayView) d;
        myViewCommands = new ArrayList<ViewCommand>();
        addViewCommands();
    }

    public void addViewCommands () {
        CommandViewBuilder builder = new CommandViewBuilder(myDisplayView);
        myViewCommands = builder.populateCommandsList();
    }

    // add the rest of the view commands (commands that don't concern model)

    public void sendAction (String input) {
        String commandAction = extractCommandAction(input);
        for (ViewCommand v : myViewCommands) {
            if (v.hasCommand(commandAction)) {
                v.executeCommand();
                return;
            }
        }

        sendToModel(input);

    }

    private String extractCommandAction (String input) {
        return input.split(" ")[0];
    }

    public void sendToModel (String input) {
        setStringCommand(input); // prepares the string command for the bundle
        setProcessable(input); // prepares the Processable for the bundle
        createBundle();
        notifyObservers();
    }

    public void setStringCommand (String input) {
        myStringCommand = input;
    }

    public void setProcessable (String input) {
        int id = extractIDFromString(input);
        myProcessable = myDisplayView.getProcessableByID(id);
    }

    private int extractIDFromString (String input) {
        String[] array = input.split(", ");
        if (array.length < 2) return 0;
        return Integer.parseInt(array[1]);
    }

    public void createBundle () {
        myCommandBundle = new CommandBundle(myStringCommand, myProcessable);
    }

    public CommandBundle getBundle () {
        return myCommandBundle;
    }

}
