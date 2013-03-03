package controller;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import command.CommandPreParser;
import model.Model;
import util.Processable;
import view.DisplayView;
import view.IView;
import view.InputView;


public class Controller implements Observer {

    private static final Dimension myDisplayViewSize = new Dimension(500, 500);
    private static final Dimension myInputViewSize = new Dimension(500, 600);
    private DisplayView myDisplayView;
    private IView myInputView;
    private List<IView> myViewList;
    private Model myModel;
    private CommandPreParser myCommandParser;


    public Controller () {
        myModel = new Model();
        myDisplayView = new DisplayView(myDisplayViewSize);
        
        myCommandParser = new CommandPreParser(myDisplayView);
        myCommandParser.addObserver(this);
        
        myInputView = new InputView("Command Inputs", "English", myCommandParser, myInputViewSize);
    }

    public void addView (IView view) {
        myViewList.add(view);
    }

    @Override
    public void update (Observable o, Object a) { // why not just pass in CommandBundle?? A: It has
                                                  // to be passed as a generic object, then we
                                                  // typecast

        // Code below this point is the View part preparing the CommandBundle
        CommandPreParser myParser = (CommandPreParser) a;
        String myCommand = myParser.getParsedString();
        
        // WE PASS MYCOMMAND TO THE MODEL TO PROCESS FURTHER, THEN UPDATE DISPLAYVIEW
        
        /*
        // Code below this point is for the Model to handle
        String commandID = myBundle.getStringCommand().split(" ")[0];
        myEncodeMap.get(commandID).encode(myBundle);

        myModel.encode(myBundle);
        
        // Get the modified processable back
        Processable modifiedProcessable = myModel.getProcessable();
        myDisplayView.updateMovable(modifiedProcessable);
        */
        myDisplayView.paint();
    }

}
