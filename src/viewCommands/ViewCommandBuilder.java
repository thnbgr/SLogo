package viewCommands;

import java.util.ArrayList;
import java.util.List;
import command.Command;
import view.DisplayView;
import viewCommands.*;


public class ViewCommandBuilder {

    private DisplayView myDisplayView;
    private List<Command> myViewCommandsList;

    public ViewCommandBuilder (DisplayView d) {
        myDisplayView = d;
        myViewCommandsList = new ArrayList<Command>();
    }

    public List<Command> populateCommandsList () {
        myViewCommandsList.add(new ClearScreen(myDisplayView));
        myViewCommandsList.add(new PenDown(myDisplayView));
        myViewCommandsList.add(new PenUp(myDisplayView));
        myViewCommandsList.add(new ShowTurtle(myDisplayView));
        myViewCommandsList.add(new HideTurtle(myDisplayView));
        myViewCommandsList.add(new XCor(myDisplayView));
        myViewCommandsList.add(new YCor(myDisplayView));
        return myViewCommandsList;
    }

}
