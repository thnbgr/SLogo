package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import model.Model;
import util.Colors;
import util.Drawable;
import util.Line;
import util.Location;
import util.Sprite;
import util.Turtle;

/**
 * DisplayView shows the turtle
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class DisplayView extends JComponent {
    // better way to think about timed events (in milliseconds)
    /** */
    public static final int ONE_SECOND = 1000;    
    /** */
    public static final int FRAMES_PER_SECOND = 25;
    /** */
    public static final int DEFAULT_DELAY = ONE_SECOND / FRAMES_PER_SECOND;   
    /** */
    public static final int TURTLE_SIZE = 50;
    private static final long serialVersionUID = 1L;
    private static Dimension ourDefaultTurtleSize = new Dimension(TURTLE_SIZE, TURTLE_SIZE);
    private static Location ourDefaultTurtleLocation;
    private List<Turtle> myTurtles;
    private Turtle myTurtle;
    private int myAssignID;
    private Colors myColors;
    // drives the animation
    private Timer myTimer;
    private JFrame myJFrame;

    /**
     * Sets the size of the display view
     * @param size is the size of the display
     */
    public DisplayView(Dimension size) {
        ourDefaultTurtleLocation = new Location(size.getHeight() / 2, size.getWidth() / 2);
        setPreferredSize(size);
        setSize(size);
        myAssignID = 0;
        myTurtles = new ArrayList<Turtle>();
        myColors = new Colors();
        setVisible(true);
    }

    private void setTurtlesID (Turtle d) {
        d.setID(myAssignID);
        myAssignID++;
    }

    public Colors getColors() {
        return myColors;
    }
    
    public void updateTurtleColors() {
        for (Turtle d : myTurtles) {
            d.setColors(myColors);
        }
        
    }
    
    
    /**
     * Sets the size of the display view
    * @param pen used to paint shape on the screen
     */
    @Override
    public void paint (Graphics pen) {
        pen.setColor(Color.WHITE);
        for (Turtle d : myTurtles) {
            d.paint((Graphics2D) pen);
        }
        
    }
    
    /**
     * Start the animation.
     */
    public void start () {
        // create a timer to animate the canvas
        myTimer = new Timer(DEFAULT_DELAY, 
            new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    step();
                }
            });
        // start animation
        myTimer.start();
    }
    
    /**
     * Take one step in the animation.
     */
    public void step () {
        repaint();
    }

    /**
     * Stop the animation.
     */
    public void stop () {
        myTimer.stop();
    }
    

    /**
     * Add turtle to myDrawables
     */
    public void addTurtle () {
        myTurtle = new Turtle(ourDefaultTurtleLocation, ourDefaultTurtleSize, myColors);
        setTurtlesID(myTurtle);
        myTurtles.add(myTurtle);
    }
    
    public void addTurtle (Turtle t) {
        setTurtlesID(t);
        myTurtles.add(t);
    }
    
    /**
     * Add turtle to myDrawables
     */
    public Turtle getTurtle() {
        return myTurtle;
    }
    
    public boolean hasTurtle() {
        return myTurtle == null;
    }

    /**
     * get a drawable given it's ID
     * @param i is ID of drawable
     */
    public Drawable getTurtleByID (int i) {
        for (Turtle d : myTurtles) {
            if (d.getID() == i) {
                return d;
            }
        }
        return null;
    }

    /**
     * return myDrawables
     */
    public List<Turtle> getTurtles () {
        return myTurtles;
    }

    /**
     * cleaer myDrawables
     */
    public void clear () {
        myTurtles = new ArrayList<Turtle>();
        myAssignID = 0;
    }

    public void addFrame (JFrame frame) {
        myJFrame = frame;
        
    }
    
    public JFrame getFrame () {
        return myJFrame;
    }

    

}
