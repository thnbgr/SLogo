package command;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
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
        ResourceBundle rb = ResourceBundle.getBundle("resources.English");
        for (String key : rb.keySet()) {
            if (key.startsWith("CMD")) {
                try {
                    Class<?> cmdClass = Class.forName(rb.getString(key));
                    Command cmd = (Command) cmdClass.getConstructor(DisplayView.class).newInstance(myDisplayView);
                    myViewCommandsList.add(cmd);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


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
