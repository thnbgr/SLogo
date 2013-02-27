package command;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import view.Processable;

public class CommandParser extends Observable {

    private String myStringCommand;
    private Processable myProcessable;
    
    public CommandParser () {
    }
    
    public void setInput (String input) {
        myStringCommand = input;
        System.out.println(myStringCommand);
    }
    
    public CommandBundle bundleCommand () {
        return new CommandBundle (myStringCommand, myProcessable);
    }
    
}
