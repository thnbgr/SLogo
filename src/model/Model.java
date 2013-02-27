package model;

import util.Processable;
import command.CommandBundle;

public class Model {
    
    private Processable myProcessable;

    public void encode (CommandBundle c) {
        myProcessable = c.getProcessable();
    }

    public Processable getProcessable () {
        return myProcessable;
    }
}
