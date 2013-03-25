package controller;

import command.CommandBuilder;
import command.CommandPerformer;
import command.CommandPreParser;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import view.DisplayView;


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
    private static final int FIELD_SIZE = 30;
    private static final Dimension DISPLAY_VIEW_SIZE = new Dimension(500, 500);
    private DisplayView myDisplayView;
    private JTextArea myTextArea;
    private ModelController myModelController;
    private CommandPreParser myCommandPreParser;
    private CommandPerformer myCommandPerformer;
    private CommandBuilder myCommandBuilder;
    private FocusListener myFocusListener;
    private ActionListener myActionListener;


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
        
        myCommandPreParser = new CommandPreParser(myCommandBuilder);
        myCommandPreParser.addObserver(this);

        myCommandPerformer = new CommandPerformer(myCommandBuilder);

        createOutputJFrame();

    }

    private void createOutputJFrame () {
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add our user interface components
        makeListeners();
        frame.getContentPane().add(myDisplayView, BorderLayout.EAST);
        frame.getContentPane().add(makeTextField(), BorderLayout.NORTH);
        frame.getContentPane().add(makeDisplay(), BorderLayout.WEST);
        // display them
        frame.pack();
        frame.setVisible(true);
        myDisplayView.addTurtle();
        myDisplayView.start();
    }
    

    /**
     * Create a standard text field (a single line that responds to enter being
     * pressed as an ActionEvent) that listens for a variety of kinds of events
     * This text field is where the user will input commands
     */
    protected JTextField makeTextField () {
        JTextField result = new JTextField(FIELD_SIZE);
        result.addFocusListener(myFocusListener);
        result.addActionListener(myActionListener);
        return result;
    }
    
    /**
     * Create all the listeners so they can be later assigned to specific
     * components.
     * 
     * Note, since these classes will not ever be used by any other class, make
     * them inline (i.e., as anonymous inner classes) --- saves making a
     * separate file for one line of actual code.
     */
    public void makeListeners () {
        // listener for "high-level" events, i.e., those made
        // up of a sequence of low-level events, like a button
        // press (mouse down and up within a button object)
        myActionListener = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                //echo("action", e);
                myCommandPreParser.sendAction(e.getActionCommand());
                showMessage(e.getActionCommand());
            }
        };
        // listener for low-level focus events, i.e., the mouse
        // entering/leaving a component so you can type in it
        myFocusListener = new FocusListener() {
            @Override
            public void focusGained (FocusEvent e) {
                echo("gained", e);
            }
            @Override
            public void focusLost (FocusEvent e) {
                echo("lost", e);
            }
        };
    }
   
    
    
    /**
     * Echo other events (e.g., Focus)
     */
    private void echo (String s, AWTEvent e) {
        //showMessage(s + " " + e);
    }

    /**
     * Display any string message in the main text area.
     * 
     * @param message message to display
     */
    public void showMessage (String message) {
        myTextArea.append(message + "\n");
        myTextArea.setCaretPosition(myTextArea.getText().length());
    }
    
    public void receiveReturnMessage (String i) {
        showMessage("return: " + i);
        
    }

    /**
     * Create a display area for showing out to the user, since it may display
     * lots of text, make it automatically scroll when needed...this is just temporary
     */
    protected JComponent makeDisplay () {
        // create with size in rows and columns
        myTextArea = new JTextArea(FIELD_SIZE, FIELD_SIZE);
        return new JScrollPane(myTextArea);
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
            receiveReturnMessage(myCommandPerformer.sendAction(myCommand)+"");
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
