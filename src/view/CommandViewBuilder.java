package view;

import java.util.ArrayList;
import java.util.List;
import viewCommands.*;


public class CommandViewBuilder {

    private DisplayView myDisplayView;
    private List<ViewCommand> myViewCommandsList;

    public CommandViewBuilder (DisplayView d) {
        myDisplayView = d;
        myViewCommandsList = new ArrayList<ViewCommand>();
    }

    public List<ViewCommand> populateCommandsList () {
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
