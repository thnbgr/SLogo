package util;

import java.awt.Dimension;
import java.awt.Graphics2D;

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
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("turtle.png");
    private boolean myIsPenUp;    
    // state
    private Location myCenter;
    private Vector myVelocity;
    private Dimension mySize;
    // keep copies of the original state so shape can be reset as needed
    private Location myOriginalCenter;
    private Vector myOriginalVelocity;
    private Dimension myOriginalSize;
    private Pixmap myOriginalView;


    public Turtle() {
        
    }
    
    public Turtle(Pixmap image, Location center, Dimension size) {
        this(image, center, size, new Vector());
    }
    
    /**
     * Create a shape at the given position, with the given size, velocity, and color.
     */
    public Turtle (Pixmap image, Location center, Dimension size, Vector velocity) {
        // make copies just to be sure no one else has access
        myOriginalCenter = new Location(center);
        myOriginalSize = new Dimension(size);
        myOriginalVelocity = new Vector(velocity);
        myOriginalView = new Pixmap(image);
        reset();
    }
    /**
     * Sets pen to be down
     */
    public void setPenDown() {
        myIsPenUp = false;
    }
    
    /**
     * Sets pen to be up
     */
    public void setPenUp() {
        myIsPenUp = true;
    }
    
    /**
     * Returns myIsPenUp
     */
    public boolean isPenDown() {
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
        DEFUALT_IMAGE.paint(pen, myCenter, mySize, myVelocity.getDirection());
    }
}
