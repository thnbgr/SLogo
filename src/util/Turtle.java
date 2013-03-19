package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


/**
 * Create Turtle
 * 
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class Turtle extends Sprite {
    /**
     */
    public static final Pixmap DEFAULT_IMAGE = new Pixmap("turtle.png");
    /**
     */
    public static final Location DEFAULT_LOCATION = new Location(100, 100);
    /**
     */
    public static final Dimension DEFAULT_SIZE = new Dimension(50, 50);
    private List<Line> myLines;
    private boolean myIsPenUp;
    // state
    private Dimension mySize;
    // keep copies of the original state so shape can be reset as needed
    private Location myOriginalCenter;
    private Vector myOriginalVelocity;
    private Dimension myOriginalSize;

    /**
     */
    public Turtle () {
        this(DEFAULT_LOCATION, DEFAULT_SIZE);
    }
    
    /**
     * Constructor with center and size
     * @param center of turtle
     * @param size of turtle
     */
    public Turtle (Location center, Dimension size) {
        this(center, size, new Vector());
    }

    /**
     * Create a shape at the given position, with the given size, velocity, and color.
     * @param center of turtle
     * @param size of turtle
     * @param velocity of vector
     */
    public Turtle (Location center, Dimension size, Vector velocity) {
        // make copies just to be sure no one else has access
        myLines = new ArrayList<Line>();
        myOriginalCenter = new Location(center);
        myOriginalSize = new Dimension(size);
        myOriginalVelocity = new Vector(velocity);
        setVisible(true);
        setPenUp();
        reset();
    }

    /**
     * Sets pen to be down
     */
    public void setPenDown () {
        myIsPenUp = false;
    }

    /**
     * Sets pen to be up
     */
    public void setPenUp () {
        myIsPenUp = true;
    }

    /**
     * Returns !myIsPenUp
     */
    public boolean isPenDown () {
        return !myIsPenUp;
    }
    
    /**
     * Returns myIsPenUp
     */
    public boolean isPenUp () {
        return myIsPenUp;
    }

    /**
     * Reset shape back to its original values.
     */
    public void reset () {
        myCenter = new Location(myOriginalCenter);
        mySize = new Dimension(myOriginalSize);
        myVelocity = new Vector(myOriginalVelocity);
    }

    /**
     * Display this shape on the screen.
     * @param pen draws shape
     */
    public void paint (Graphics2D pen) {
        if (isVisible()) {
            DEFAULT_IMAGE.paint(pen, myCenter, mySize, myVelocity.getDirection());
        }
        if (isPenUp()) {
            for (Line l : myLines) {
                l.paint((Graphics) pen);
            }
        }
    }
    
    /**
     * Updates the turtle's location based on command
     * Adds turtle tracks based on turtle's movements
     * @param distance is amount for turtle to move
     */
    public void move (int distance) {
        myVelocity.setMagnitude(distance);
        Location newCenter = myCenter;
        newCenter.translate(myVelocity);
        addLine(myCenter, new Location(myCenter.x, myCenter.y));
        myCenter.translate(myVelocity);
    }
    
    /**
     * Amount for turtle to move
     * @param angle for turtle to move
     */
    public void turn (int angle) {
        myVelocity.setDirection(myVelocity.getDirection() + angle);
    }
    
    /**
     * Add line to myLines
     * @param start location of line
     * @param end location of line
     */
    public void addLine(Location start, Location end) {
        myLines.add(new Line(start, end, Color.black));
    }

    public void moveToCenter () {
        // TODO Auto-generated method stub
        
    }

}
