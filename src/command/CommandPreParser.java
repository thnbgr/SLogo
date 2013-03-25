package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import view.DisplayView;


/**
 * 
 * This is the first parser that receives *all* commands.
 * It checks to see if the command can be processed by view
 * If not, it passes it to the model
 * In both cases, we receive a return value, and pass it to the view
 * 
 * ViewController observes CommandPreParser
 * 
 * @author Eric Wu
 * @author Natalia Carvalho
 * 
 */
public class CommandPreParser extends Observable {

    private List<Command> myViewCommands;
    private CommandBuilder myCommandBuilder;

    public CommandPreParser (CommandBuilder c) {
        myViewCommands = new ArrayList<Command>();
        myCommandBuilder = c;
        addViewCommands();
    }

    public void addViewCommands () {
        myViewCommands = myCommandBuilder.populateViewCommandsList();
    }

    // add the rest of the view commands (commands that don't concern model)

    public int sendAction (String input) {

        input = input.toLowerCase();
        int r = 0;
        for (Command v : myViewCommands) {
            for (String s : v.getCommands()) {
                if (input.contains(s)) {
                    r = v.executeCommand();
                    input = input.replace(s, r + "");
                }
            }
        }
        setChanged();
        notifyObservers(input);
        return r;

    }

}
