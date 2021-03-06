package util;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


/**
 * Create Turtle 
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class Turtle extends Sprite {
    /**
     */
    public static Pixmap DEFAULT_IMAGE = new Pixmap("turtle.png");
    /**
     */
    public static int DEFAULT_PEN_SIZE = 1;

    /**
     */
    public static final Location DEFAULT_LOCATION = new Location(250, 250);
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

    private Colors myColors;
    private BasicStroke myPen;

    /**
     */
    public Turtle () {
        this(DEFAULT_LOCATION, DEFAULT_SIZE, new Vector(), new Colors());
    }
    
    /**
     * Constructor with center and size
     * @param center of turtle
     * @param size of turtle
     * @param c is colors object
     */
    public Turtle (Location center, Dimension size, Colors c) {
        this(center, size, new Vector(), c);
    }
    
    public Turtle (Location center, Dimension size) {
        this(center, size, new Vector(), new Colors());
    }

    /**
     * Create a shape at the given position, with the given size, velocity, and color.
     * @param center of turtle
     * @param size of turtle
     * @param velocity of vector
     * @param c is color object
     */
    public Turtle (Location center, Dimension size, Vector velocity, Colors c) {
        // make copies just to be sure no one else has access
        myLines = new ArrayList<Line>();
        myOriginalCenter = new Location(center);
        myOriginalSize = new Dimension(size);
        myOriginalVelocity = new Vector(velocity);
        myColors = c;
        myPen = new BasicStroke(DEFAULT_PEN_SIZE);
        setVisible(true);
        setPenDown();
        reset();
    }
    
    
    public Turtle stamp() {
        return new Turtle(getMyCenter(), mySize);
    }
    
    /**
     * Change default turtle image to another pixmap image
     * @param image is the new image
     */
    public void changeTurtleImage (Pixmap image) {
        DEFAULT_IMAGE = image;
    }

    /**
     * Set myColors
     * @param c is new color
     */
    public void setColors (Colors c) {
        myColors = c;
    }
    
    /**
     * clear the lines
     */
    public void clearLines () {
        myLines.clear();
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
        setMyCenter(new Location(myOriginalCenter));
        mySize = new Dimension(myOriginalSize);
        setMyVelocity(new Vector(myOriginalVelocity));
    }

    /**
     * Display this shape on the screen.
     * @param pen draws shape
     */
    public void paint (Graphics2D pen) {
        pen.setStroke(myPen);
        if (isVisible()) {
            DEFAULT_IMAGE.paint(pen, getMyCenter(), mySize, getMyVelocity().getDirection());
        }
        for (Line l : myLines) {
            l.paint((Graphics) pen);
        }
    }
    
    /**
     * Updates the turtle's location based on command
     * Adds turtle tracks based on turtle's movements
     * @param distance is amount for turtle to move
     */
    public void move (int distance) {
        getMyVelocity().setMagnitude(distance);
        Location newCenter = getMyCenter();
        newCenter.translate(getMyVelocity());
        if (isPenDown()) {
            addLine(getMyCenter(), new Location(getMyCenter().x, getMyCenter().y));
        }
        getMyCenter().translate(getMyVelocity());
    }
    
    /**
     * Amount for turtle to move
     * @param angle for turtle to move
     */
    public void turn (int angle) {
        getMyVelocity().setDirection(getMyVelocity().getDirection() + angle);
    }
    
    /**
     * Add line to myLines
     * @param start location of line
     * @param end location of line
     */
    public void addLine(Location start, Location end) {
        myLines.add(new Line(start, end, myColors.getLineColor()));
    }

    /**
     * Moves turtle back to original position
     */
    public void moveToCenter () {
        setMyCenter(myOriginalCenter);
    }

    /**
     * Sets pen size to given position
     * @param penSize is width of pen
     */
    public void setPenSize (int penSize) {
        myPen = new BasicStroke(penSize);
    }

    /**
     * Gets default image
     */
    public Pixmap getImage () {
        return DEFAULT_IMAGE;
    }

}

