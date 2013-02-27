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
        myViewCommandsList.add(new Make(myDisplayView));
        return myViewCommandsList;
    }

}
