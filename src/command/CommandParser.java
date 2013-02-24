package command;

import java.util.Observable;
import java.util.Observer;
import view.Processable;

public class CommandParser extends Observable {

    private String myStringCommand;
    private Processable myProcessable;
    
    public CommandParser () {
    }
    
    public void setInput () {
        
    }
    
    public CommandBundle bundleCommand () {
        return new CommandBundle (myStringCommand, myProcessable);
    }
    
}
