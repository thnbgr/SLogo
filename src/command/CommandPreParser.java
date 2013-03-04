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
import view.DisplayView;
import view.IView;
import viewCommands.*;


/**
 * 
 * This is the first parser that receives *all* commands.
 * It checks to see if the command can be processed by view
 * If not, it passes it to the model
 * In both cases, we receive a return value, and pass it to the view
 * 
 * @author Eric Wu
 * 
 */
public class CommandPreParser extends Observable {

    private String myStringCommand;
    // private Processable myProcessable;
    private DisplayView myDisplayView;
    // private CommandBundle myCommandBundle;
    private List<Command> myViewCommands;

    public CommandPreParser (IView d) {
        myDisplayView = (DisplayView) d;
        myViewCommands = new ArrayList<Command>();
        addViewCommands();
    }

    public void addViewCommands () {
        CommandBuilder builder = new CommandBuilder(myDisplayView);
        myViewCommands = builder.populateViewCommandsList();
    }

    // add the rest of the view commands (commands that don't concern model)

    public void sendAction (String input) { // fd sum xcor 50
        input = input.toLowerCase();
        for (Command v : myViewCommands) {
            for (String s : v.getCommands()) {
                if (input.contains(s)) {
                    int r = v.executeCommand();
                    input = input.replace(s, r + "");
                }
            }
        }
        sendToController(input);

    }

    public void sendToController (String input) {
        myStringCommand = input;
        notifyObservers();
    }
    
    public String getParsedString () {
        return myStringCommand;
    }
    /*
     * public void setStringCommand (String input) {
     * myStringCommand = input;
     * }
     * 
     * public void setProcessable (String input) {
     * int id = extractIDFromString(input);
     * myProcessable = myDisplayView.getProcessableByID(id);
     * }
     * 
     * private int extractIDFromString (String input) {
     * String[] array = input.split(", ");
     * if (array.length < 2) return 0;
     * return Integer.parseInt(array[1]);
     * }
     * 
     * public void createBundle () {
     * myCommandBundle = new CommandBundle(myStringCommand, myProcessable);
     * }
     * 
     * public CommandBundle getBundle () {
     * return myCommandBundle;
     * }
     */

}
