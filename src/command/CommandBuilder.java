package command;

import java.util.ArrayList;
import java.util.List;
import view.DisplayView;
import viewCommands.*;
import DisplayCommands.*;


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
        myViewCommandsList.add(new BGColor(myDisplayView));
        return myViewCommandsList;
    }

    public List<Command> populateDisplayCommandsList () {
        myDisplayCommandsList.add(new Back(myDisplayView));
        myDisplayCommandsList.add(new Forward(myDisplayView));
        myDisplayCommandsList.add(new Left(myDisplayView));
        myDisplayCommandsList.add(new Right(myDisplayView));
        myDisplayCommandsList.add(new SetHeading(myDisplayView));
        myDisplayCommandsList.add(new SetXY(myDisplayView));
        myDisplayCommandsList.add(new Towards(myDisplayView));

        return myDisplayCommandsList;
    }

}
