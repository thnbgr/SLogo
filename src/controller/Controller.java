package controller;

import command.CommandPerformer;
import command.CommandPreParser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import model.Model;
import view.DisplayView;
import view.InputView;

/**
 * Controller handles the communication between model and view
 * @author Eric Wu
 * @author Natalia Carvalho
 * 
 */
public class Controller implements Observer {
    /**
     */
    public static final String TITLE = "Output Display";
    private static final Dimension DISPLAY_VIEW_SIZE = new Dimension(500, 500);
    private static final Dimension INPUT_VIEW_SIZE = new Dimension(500, 600);
    private DisplayView myDisplayView;
    private InputView myInputView;
    private Model myModel;
    private CommandPreParser myCommandPreParser;
    private CommandPerformer myCommandPerformer;

    /**
     * Constructor for controller
     * @param model is the model that we communicate with
     */
    public Controller (Model model) {
        myModel = model;
        myDisplayView = new DisplayView(DISPLAY_VIEW_SIZE);
        myCommandPreParser = new CommandPreParser(myDisplayView);
        myCommandPreParser.addObserver(this);
        myCommandPerformer = new CommandPerformer(myDisplayView);
        createOutputJFrame();
        myInputView =
                new InputView("Command Inputs", "English", myCommandPreParser, INPUT_VIEW_SIZE);

    }

    private void createOutputJFrame () {
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add our user interface components
        frame.getContentPane().add(myDisplayView, BorderLayout.CENTER);
        // display them
        frame.pack();
        frame.setVisible(true);
        myDisplayView.addTurtle();
        myDisplayView.start();
    }

    /**
     * This is the observer
     * @param o is the observable
     * @param a is the string that is passed from observed
     */
    public void update (Observable o, Object a) {
        String myCommand = (String) a;


        // This is where we send it to the model parser
        // We assume for now that is has been parsed

        myInputView.receiveReturnMessage(myCommandPerformer.sendAction(myCommand));
    }

}
