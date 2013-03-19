package controller;

import command.CommandBuilder;
import command.CommandPerformer;
import command.CommandPreParser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import model.Model;
import view.DisplayView;
import view.InputView;


/**
 * Controller handles the communication between model and view
 * 
 * @author Eric Wu
 * @author Natalia Carvalho
 * 
 */
public class MainController implements Observer {
    /**
     */
    public static final String TITLE = "Output Display";
    private static final Dimension DISPLAY_VIEW_SIZE = new Dimension(500, 500);
    private static final Dimension INPUT_VIEW_SIZE = new Dimension(500, 600);
    private DisplayView myDisplayView;
    private InputView myInputView;
    private ModelController myModelController;
    private CommandPreParser myCommandPreParser;
    private CommandPerformer myCommandPerformer;
    private CommandBuilder myCommandBuilder;

    /**
     * Constructor for controller
     * 
     * @param model is the model that we communicate with
     */
    public MainController (ModelController modelcontroller) {

        myDisplayView = new DisplayView(DISPLAY_VIEW_SIZE);

        
        myModelController = modelcontroller;
        myModelController.addObserver(this);

        myCommandBuilder = new CommandBuilder(myDisplayView);
        
        myCommandPreParser = new CommandPreParser(myDisplayView, myCommandBuilder);
        myCommandPreParser.addObserver(this);

        myCommandPerformer = new CommandPerformer(myDisplayView, myCommandBuilder);

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
     * This is the observer, it is called when CommandPreParser pre-parses the command string
     * 
     * @param o is the observable
     * @param a is the string that is passed from observed
     */
    public void update (Observable o, Object a) {
        
        String myCommand = (String) a;

        if (o.getClass().getName().equals("controller.ModelController")) {
            myInputView.receiveReturnMessage(myCommandPerformer.sendAction(myCommand));
        }
        else {

            // call the method under model to parse the command
            try {
                myModelController.checkInputValidAndProcess(myCommand);
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
