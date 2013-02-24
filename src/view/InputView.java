package view;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.*;
import java.util.ResourceBundle;
import javax.swing.*;



/**
 * 
 * @author Eric Wu
 * @author Natalia Carvalho
 *
 */
public class InputView extends JFrame {
    
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private ActionListener myActionListener;
    private MouseListener myMouseListener;
    // most GUI components will be temporary variables,
    // only store components you need to refer to later
    private JTextArea myTextArea;
    private JFileChooser myChooser;
    private FocusListener myFocusListener;
    private int myLastKeyPressed;
    private static final int FIELD_SIZE = 30;
    // this constant should be defined by Java, not me :( 
    private static final String USER_DIR = "user.dir";
    // get strings from resource file
    private ResourceBundle myResources;
    
    public static final int NO_KEY_PRESSED = -1;
    public static final Point NO_MOUSE_PRESSED = null;
    
    private Point myLastMousePosition;



    public InputView (String title, String language) {
        // set properties of frame
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // create a single file chooser for the entire example
        myChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        // create and arrange sub-parts of the GUI
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        // create listeners that will respond to events
        makeListeners();
        getContentPane().add(makeInput(), BorderLayout.NORTH);
        
        // size and display the GUI
        pack();
        setVisible(true);
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
                echo("action", e);
            }
        };
    }
    
    /**
     * Echo key presses by showing important attributes
     */
    private void echo (String s, KeyEvent e) {
        showMessage(s + " char:" + e.getKeyChar() + " mod: " +
                    KeyEvent.getKeyModifiersText(e.getModifiers()) + " mod: " +
                    KeyEvent.getKeyText(e.getKeyCode()));
    }
    
    /**
     * Echo action events including time event occurs
     */
    private void echo (String s, ActionEvent e) {
        showMessage(s + " = " + e.getActionCommand() + " " + e.getWhen());
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
    
    public void clearMessage() {
        myTextArea.append("");
    }
    
    /**
     * Create an input area for the user --- 
     *   text field for text
     */
    protected JComponent makeInput () {
        JPanel result = new JPanel();
        result.add(makeTextField());
        result.add(makeDisplay());
        return result;
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
     * Create a display area for showing out to the user, since it may display
     * lots of text, make it automatically scroll when needed
     */
    protected JComponent makeDisplay () {
        // create with size in rows and columns
        myTextArea = new JTextArea(FIELD_SIZE, FIELD_SIZE);
        return new JScrollPane(myTextArea);
    }
    
    /**
     * Java starts the program here and does not end until GUI goes away
     * 
     * @param args command-line arguments
     */
    public static void main (String[] args) {
        new InputView("Command Inputs", "English");
    }

}
