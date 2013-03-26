package command;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import view.DisplayView;

/**
 * 
 * @author Eric Wu
 *
 */

public class CommandBuilder {

    private DisplayView myDisplayView;
    private ResourceBundle myResources;
    private List<Command> myViewCommandsList, myDisplayCommandsList;

    public CommandBuilder (DisplayView d, ResourceBundle r) {
        myResources = r;
        myDisplayView = d;
        myViewCommandsList = new ArrayList<Command>();
        myDisplayCommandsList = new ArrayList<Command>();
    }

    public List<Command> populateViewCommandsList () {

        String packageName = "viewCommands";

        String urlString = System.getProperty("user.dir") + "/src/" + packageName;
        File f = new File(urlString);
        File[] files = f.listFiles();
        for (File name : files) {
            String className = name.getName().split(".java")[0];

            Command c = null;
            try {
                c = (Command) Class.forName(packageName + "." + className).newInstance();
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            c.addDisplayView(myDisplayView);
            myViewCommandsList.add(c);

        }

        /*
         * myViewCommandsList.add(new ClearScreen(myDisplayView));
         * myViewCommandsList.add(new PenDown(myDisplayView));
         * myViewCommandsList.add(new PenUp(myDisplayView));
         * myViewCommandsList.add(new PenDownQ(myDisplayView));
         * myViewCommandsList.add(new ShowTurtle(myDisplayView));
         * myViewCommandsList.add(new ShowingQ(myDisplayView));
         * myViewCommandsList.add(new HideTurtle(myDisplayView));
         * myViewCommandsList.add(new XCor(myDisplayView));
         * myViewCommandsList.add(new YCor(myDisplayView));
         * myViewCommandsList.add(new SetBackground(myDisplayView));
         * myViewCommandsList.add(new SetPenColor(myDisplayView));
         * myViewCommandsList.add(new Home(myDisplayView));
         * myViewCommandsList.add(new SetPenSize(myDisplayView));
         * myViewCommandsList.add(new SetShape(myDisplayView));
         * myViewCommandsList.add(new Shape(myDisplayView));
         * myViewCommandsList.add(new Stamp(myDisplayView));
         * myViewCommandsList.add(new ClearStamps(myDisplayView));
         * myViewCommandsList.add(new ID(myDisplayView));
         * myViewCommandsList.add(new Tell(myDisplayView));
         * myViewCommandsList.add(new TellEven(myDisplayView));
         * myViewCommandsList.add(new TellOdd(myDisplayView));
         * myViewCommandsList.add(new Grid(myDisplayView));
         */

        /**
         * This takes care of the languages.
         */
        
        for (Command c : myViewCommandsList) {
            ArrayList<String> commands = (ArrayList<String>) c.getCommands();
            for (int i = 0; i < commands.size(); i++) {
                if (!myResources.containsKey(commands.get(i))) continue;
                String temp = myResources.getString(commands.get(i));
                commands.set(i, temp);
            }
        }

        return myViewCommandsList;
    }

    public List<Command> populateDisplayCommandsList () {

        String packageName = "DisplayCommands";

        String urlString = System.getProperty("user.dir") + "/src/" + packageName;
        File f = new File(urlString);
        File[] files = f.listFiles();
        for (File name : files) {
            String className = name.getName().split(".java")[0];

            Command c = null;
            try {
                c = (Command) Class.forName(packageName + "." + className).newInstance();
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            c.addDisplayView(myDisplayView);
            myViewCommandsList.add(c);

        }

        /*
         * myDisplayCommandsList.add(new Back(myDisplayView));
         * myDisplayCommandsList.add(new Forward(myDisplayView));
         * myDisplayCommandsList.add(new Left(myDisplayView));
         * myDisplayCommandsList.add(new Right(myDisplayView));
         * myDisplayCommandsList.add(new SetHeading(myDisplayView));
         * myDisplayCommandsList.add(new SetXY(myDisplayView));
         * myDisplayCommandsList.add(new Towards(myDisplayView));
         */
        

        for (Command c : myViewCommandsList) {
            ArrayList<String> commands = (ArrayList<String>) c.getCommands();
            for (int i = 0; i < commands.size(); i++) {
                if (!myResources.containsKey(commands.get(i))) continue;
                String temp = myResources.getString(commands.get(i));
                commands.set(i, temp);
            }
        }
        return myDisplayCommandsList;

    }

}
