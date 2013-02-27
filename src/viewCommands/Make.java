package viewCommands;

import java.util.ArrayList;
import util.Turtle;
import view.DisplayView;


public class Make extends ViewCommand {

    public Make (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("make");
    }

    public void executeCommand () {
        Turtle t = new Turtle();
        myDisplayView.addSprite(t);
    }

}
