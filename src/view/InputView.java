package view;

import command.CommandPreParser;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * 
 * @author Eric Wu
 * @author Natalia Carvalho
 * 
 */
public class InputView extends JFrame implements IView {

    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private static final int FIELD_SIZE = 30;
    private static final String USER_DIR = "user.dir";
    // Listeners
    private ActionListener myActionListener;
    // most GUI components will be temporary variables,
    // only store components you need to refer to later
    private JTextArea myTextArea;
    private JFileChooser myChooser;
    private FocusListener myFocusListener;
    // this constant should be defined by Java, not me :(
    // get strings from resource file
    private ResourceBundle myResources;
    private CommandPreParser myCommandParser;

    /**
     * InputView Constructor
     *@param title is the title of window
     *@param language is the default language
     *@param parser is where the information is passed to
     *@param size is the size of the screen
     */
    public InputView (String title, String language, CommandPreParser parser, Dimension size) {
        //create new command parser
        myCommandParser = parser;
        // set properties of frame
        setTitle(title);
        setPreferredSize(size);
        setSize(size);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // create a single file chooser for the entire example
        myChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        // create and arrange sub-parts of the GUI
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        // create listeners that will respond to events
        makeListeners();
        getContentPane().add(makeInput(), BorderLayout.NORTH);

        // create app menus
        setJMenuBar(makeMenus());
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
                myCommandParser.sendAction(e.getActionCommand());
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
     * Echo action events including time event occurs
     */
    private void echo (String s, ActionEvent e) {
        showMessage(s + " = " + e.getActionCommand() + " " + e.getWhen());
    }

    /**
     * Echo data read from reader to display
     */
    private void echo (Reader r) {
        try {
            String s = "";
            BufferedReader input = new BufferedReader(r);
            String line = input.readLine();
            while (line != null) {
                s += line + "\n";
                line = input.readLine();
            }
            showMessage(s);
        }
        catch (IOException e) {
            showError(e.toString());
        }
    }

    /**
     * Echo display to writer
     */
    private void echo (Writer w) {
        PrintWriter output = new PrintWriter(w);
        output.println(myTextArea.getText());
        output.flush();
        output.close();
    }
    
    /**
     * Echo other events (e.g., Focus)
     */
    private void echo (String s, AWTEvent e) {
        showMessage(s + " " + e);
    }
    
    /**
     * Display any string message in a popup error dialog.
     * 
     * @param message message to display
     */
    public void showError (String message) {
        JOptionPane.showMessageDialog(this, message, 
                                      myResources.getString("ErrorTitle"),
                                      JOptionPane.ERROR_MESSAGE);
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

    /**
     * Create an input area for the user ---
     * text field for text
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
                            myCommandParser.sendAction(s);
                        }
                        reader.close();
                    }
                }
                catch (IOException io) {
                    // let user know an error occurred, but keep going
                    showError(io.toString());
                }
            }
        });
        result.add(new AbstractAction(myResources.getString("SaveCommand")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    echo(new FileWriter("demo.out"));
                }
                catch (IOException io) {
                    // let user know an error occurred, but keep going
                    showError(io.toString());
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
        return result;
    }


    @Override
    public void paint () {
        // TODO Auto-generated method stub

    }

}
