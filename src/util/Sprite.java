package util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Random;


/**
 * Sprite class
 * @author Eric Wu
 * @author Natalia Carvalho
 */
public class Sprite extends Drawable {

    protected Location myCenter;
    protected Vector myVelocity;
    protected Pixmap myPixmap;
    protected Dimension mySize;

    /**
     * Constructor
     */
    public Sprite () {

    }
    
    
    public Sprite stamp() {
        try {
            return (Sprite) this.clone();
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public void paint (Graphics2D pen) {

    }

    /**
     * Return location of sprite
     */
    public Location getLocation () {
        return myCenter;
    }
    
    /**
     * Return velocity of sprite
     */
    public Vector getVector () {
        return myVelocity;
    }

    /**
     * Update sprite location, size, velocity
     * @param center of sprite
     * @param size of sprite
     * @param velocity of sprite
     */
    public void update (Location center, Dimension size, Vector velocity) {
        myCenter = center;
        mySize = size;
        myVelocity = velocity;
    }

}
