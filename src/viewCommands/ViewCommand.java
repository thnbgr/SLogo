package viewCommands;

import java.util.List;
import view.DisplayView;

public abstract class ViewCommand {
    protected DisplayView myDisplayView;
    protected List<String> myCommands;
    public abstract int executeCommand();
    public boolean hasCommand(String s) {
        return myCommands.contains(s);
    }
    public List<String> getCommands() {
        return myCommands;
    }
    
}
