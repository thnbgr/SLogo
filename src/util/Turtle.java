package util;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
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
    public static Pixmap DEFAULT_IMAGE = new Pixmap("turtle.png");
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
    private Colors myColors;

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
        setVisible(true);
        setPenUp();
        reset();
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
 //       Stroke drawingStroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
 //       pen.setStroke(drawingStroke);
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
        myLines.add(new Line(start, end, myColors.getLineColor()));
    }

    public void moveToCenter () {
    }

}