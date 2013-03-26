package viewCommands;

import command.Command;
import java.util.ArrayList;
import view.DisplayView;

/**
 * returns shape ID of current pixmap picture
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class Shape extends Command {

    /**
     * shape constructor
     * @param m is displayview
     */
    public Shape (DisplayView m) {
        myDisplayView = m;
        myCommands = new ArrayList<String>();
        myCommands.add("shape");
        myCommands.add("sh");
    }

    @Override
    public int executeCommand () {
        return myDisplayView.getIndexByPixmap(myDisplayView.getTurtle().getImage());
    }

}
