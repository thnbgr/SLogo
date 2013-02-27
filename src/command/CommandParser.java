package command;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import util.Drawable;
import util.Processable;
import view.DisplayView;
import view.IView;


public class CommandParser extends Observable {

    private String myStringCommand;
    private Processable myProcessable;
    private DisplayView myDisplayView;
    private CommandBundle myCommandBundle;
    private List<String> myViewCommands;

    public CommandParser (IView d) {
        myDisplayView = (DisplayView) d;
        myViewCommands = new ArrayList<String>();
        addViewCommands();
    }

    public void addViewCommands () {
        myViewCommands.add("create");
        // add the rest of the view commands (commands that don't concern model)
    }

    public void sendAction (String input) {
        if (myViewCommands.contains(extractCommandAction(input))) {
            // execute command
            myDisplayView.createTurtle();
        }
        else {
            sendToModel(input);
        }
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
