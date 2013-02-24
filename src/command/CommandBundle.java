package command;

import view.Processable;

public class CommandBundle {

    private String myStringCommand;
    private Processable myProcessable;
    
    public CommandBundle (String s, Processable p) {
        myStringCommand = s;
        myProcessable = p;
    }
    
    public String getStringCommand () {
        return myStringCommand;
    }
    
    public Processable getProcessable () {
        return myProcessable;
    }
}
