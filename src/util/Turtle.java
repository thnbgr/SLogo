package util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;


/**
 * Create Turtle
 * 
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class Turtle extends Sprite {

    /**
     * Create turtle Pixmap
     */
    public static final Pixmap DEFAULT_IMAGE = new Pixmap("turtle.png");
    public static final Location DEFAULT_LOCATION = new Location(100, 100);
    public static final Dimension DEFAULT_SIZE = new Dimension(50, 50);

    
    private boolean myIsPenUp;
    // state
    private Dimension mySize;
    // keep copies of the original state so shape can be reset as needed
    private Location myOriginalCenter;
    private Vector myOriginalVelocity;
    private Dimension myOriginalSize;
    private Pixmap myOriginalView;


    public Turtle () {
        this(DEFAULT_LOCATION, DEFAULT_SIZE);
    }
    public Turtle(Location center, Dimension size) {
        this(center, size, new Vector());
    }

    /**
     * Create a shape at the given position, with the given size, velocity, and color.
     */
    public Turtle (Location center, Dimension size, Vector velocity) {
        // make copies just to be sure no one else has access
        myOriginalCenter = new Location(center);
        myOriginalSize = new Dimension(size);
        myOriginalVelocity = new Vector(velocity);
        myOriginalView = new Pixmap(DEFAULT_IMAGE);
        setVisible(true);
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
     * Returns myIsPenUp
     */
    public boolean isPenDown () {
        return !myIsPenUp;
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
     */
    public void paint (Graphics2D pen)
    {
        if (!isVisible()) return;
        DEFAULT_IMAGE.paint(pen, myCenter, mySize, myVelocity.getDirection());
    }

    public void moveToCenter () {
    }
    

}
