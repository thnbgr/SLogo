package command;

import java.awt.event.ActionEvent;
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

    public CommandParser (IView d) {
        myDisplayView = (DisplayView) d;
    }

    public void sendAction (String input) {
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
        if (array[1] == null) return 0;
        return Integer.parseInt(array[1]);
    }

    public void createBundle () {
        myCommandBundle = new CommandBundle(myStringCommand, myProcessable);
    }

    public CommandBundle getBundle () {
        return myCommandBundle;
    }

}
