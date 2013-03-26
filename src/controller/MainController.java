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
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import util.Pixmap;
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
    private static final String USER_DIR = "user.dir";
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private static final String language = "English";
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
    private JFileChooser myChooser;
    private ResourceBundle myResources;

    /**
     * Constructor for controller
     * 
     * @param modelcontroller is the model that we communicate with
     */
    public MainController (ModelController modelcontroller) {

        // create a single file chooser for the entire example
        myChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        // create and arrange sub-parts of the GUI
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
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
        
        frame.setJMenuBar(makeMenus());
        // display them
        frame.pack();
        frame.setVisible(true);
        myDisplayView.addFrame(frame);
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
                showMessage(e.getActionCommand());
                int r = myCommandPreParser.sendAction(e.getActionCommand());
                if (myCommandPreParser.isValidCommand()) {
                    receiveReturnMessage(r+"");
                }
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
     * @param message message to display
     */
    public void showMessage (String message) {
        myTextArea.append(message + "\n");
        myTextArea.setCaretPosition(myTextArea.getText().length());
    }
    
    /**
     * Receives a return message
     * @param i message to return
     */
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
     * Create a menu to appear at the top of the frame, 
     *   usually File, Edit, App Specific Actions, Help
     */
    protected JMenuBar makeMenus () {
        JMenuBar result = new JMenuBar();
        result.add(makeFileMenu());
        return result;
    }
    
    /**
     * Create a menu that will pop up when the menu button is pressed in the
     * frame. File menu usually contains Open, Save, and Exit
     * 
     * Note, since these classes will not ever be used by any other class, make
     * them inline (i.e., as anonymous inner classes) --- saves making a
     * separate file for one line of actual code.
     */
    protected JMenu makeFileMenu () {
        JMenu result = new JMenu(myResources.getString("FileMenu"));
        result.add(new AbstractAction(myResources.getString("OpenCommand")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    
                    int response = myChooser.showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        FileReader reader = new FileReader(myChooser.getSelectedFile());
                        BufferedReader br = new BufferedReader(reader);
                        String s;
                        while ((s = br.readLine()) != null) {
                            myCommandPreParser.sendAction(s);
                        }
                        reader.close();
                    }
                }
                catch (IOException io) {
                    // let user know an error occurred, but keep going; bug needs to be fixed
                   // showError(io.toString());
                }
            }
        });
        result.add(new JSeparator());
        result.add(new AbstractAction(myResources.getString("QuitCommand")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed (ActionEvent e) {
                // clean up any open resources, then
                // end program
                System.exit(0);
            }
        });
        result.add(new AbstractAction(myResources.getString("TurtleImageCommand")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    
                    int response = myChooser.showOpenDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        File file = myChooser.getSelectedFile();
                        BufferedImage myBufferedImage = ImageIO.read(file);
                        Pixmap myImage = new Pixmap(myBufferedImage);
                        myDisplayView.getTurtle().changeTurtleImage(myImage);

                    }
                }
                catch (IOException io) {
                    // let user know an error occurred, but keep going; bug needs to be fixed
                   // showError(io.toString());
                }
            }
        });
        return result;
    }
    
    

    /**
     * This is the observer, it is called when CommandPreParser pre-parses the command string
     * 
     * @param o is the observable
     * @param a is the string that is passed from observed
     */
    public void update (Observable o, Object a) {
        String myCommand = (String) a;

        // If the command has already been processed by Model and needs to be performed
        if (o.getClass().getName().equals("controller.ModelController")) {
            receiveReturnMessage(myCommandPerformer.sendAction(myCommand) + "");
        }
        else {
                CommandPreParser preParser = (CommandPreParser) o;
            

            // call the method under model to parse the command
                try {
                    // Called after the preprocessing and before the model processing
                    myModelController.checkInputValidAndProcess(myCommand);
                }
                
                catch (IOException e) {
                    if (!preParser.isValidCommand())
                    receiveReturnMessage("Invalid Command");

                }
                
                catch (ClassCastException e) {}
                
                catch (IndexOutOfBoundsException e) {}
                
                catch (Exception e) {
                    receiveReturnMessage("Model returned error");
                    e.printStackTrace();

                }

        }
    }
    
    

}
