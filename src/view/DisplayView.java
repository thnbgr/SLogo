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
import javax.swing.Timer;
import model.Model;
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
public class DisplayView extends JComponent implements IView {
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
    private List<Drawable> myDrawables;
    private List<Line> myLines;
    private Turtle myTurtle;
    private int myAssignID;
    // drives the animation
    private Timer myTimer;

    /**
     * Sets the size of the display view
     * @param size is the size of the display
     */
    public DisplayView(Dimension size) {
        ourDefaultTurtleLocation = new Location(size.getHeight() / 2, size.getWidth() / 2);
        setPreferredSize(size);
        setSize(size);
        myAssignID = 0;
        myDrawables = new ArrayList<Drawable>();
        myLines = new ArrayList<Line>();
        setVisible(true);
    }

    private void setDrawableID (Drawable d) {
        d.setID(myAssignID);
        myAssignID++;
    }

    
    /**
     * Sets the size of the display view
    * @param pen used to paint shape on the screen
     */
    @Override
    public void paint (Graphics pen) {
        pen.setColor(Color.WHITE);
        for (Drawable d : myDrawables) {
            d.paint((Graphics2D) pen);
        }
        for (Line l : myLines) {
            l.paint((Graphics) pen);
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
    
    @Override
    public void paint () {
        
    }
    
    /**
     * Add sprite to myDrawables
     * @param s sprite to be added to myDrawables
     */
    public void addSprite (Sprite s) {
        setDrawableID(s);
        myDrawables.add(s);
    }
    
    /**
     * Add turtle to myDrawables
     */
    public void addTurtle () {
        myTurtle = new Turtle(ourDefaultTurtleLocation, ourDefaultTurtleSize);
        addSprite(myTurtle);
    }
    
    /**
     * Add turtle to myDrawables
     */
    public Turtle getTurtle() {
        return myTurtle;
    }
    
    /**
     * Add line to myLines
     * @param start location of line
     * @param end location of line
     */
    public void addLine(Location start, Location end) {
        myLines.add(new Line(start, end, Color.black));
        repaint();
    }

    /**
     * get a drawable given it's ID
     * @param i is ID of drawable
     */
    public Drawable getDrawableByID (int i) {
        for (Drawable d : myDrawables) {
            if (d.getID() == i) {
                return d;
            }
        }
        return null;
    }

    /**
     * return myDrawables
     */
    public List<Drawable> getDrawables () {
        return myDrawables;
    }

    /**
     * cleaer myDrawables
     */
    public void clear () {
        myDrawables = new ArrayList<Drawable>();
        myAssignID = 0;
    }


}
