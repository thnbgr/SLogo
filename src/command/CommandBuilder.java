package command;

import java.util.ArrayList;
import java.util.List;
import view.DisplayView;
import viewCommands.*;


public class CommandBuilder {

    private DisplayView myDisplayView;
    private List<Command> myViewCommandsList, myDisplayCommandsList;

    public CommandBuilder (DisplayView d) {
        myDisplayView = d;
        myViewCommandsList = new ArrayList<Command>();
        myDisplayCommandsList = new ArrayList<Command>();

    }

    public List<Command> populateViewCommandsList () {
        myViewCommandsList.add(new ClearScreen(myDisplayView));
        myViewCommandsList.add(new PenDownQ(myDisplayView));
        myViewCommandsList.add(new ShowTurtle(myDisplayView));
        myViewCommandsList.add(new HideTurtle(myDisplayView));
        myViewCommandsList.add(new XCor(myDisplayView));
        myViewCommandsList.add(new YCor(myDisplayView));
        return myViewCommandsList;
    }
    
    public List<Command> populateDisplayCommandsList () {
        myDisplayCommandsList.add(new ClearScreen(myDisplayView));
        return myDisplayCommandsList;
    }

}
