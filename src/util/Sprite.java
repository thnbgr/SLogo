package util;

import java.awt.Dimension;
import java.awt.Graphics2D;


/**
 * Sprite class
 * @author Eric Wu
 * @author Natalia Carvalho
 */
public class Sprite extends Drawable {

    private Location myCenter;
    private Vector myVelocity;
    private Pixmap myPixmap;
    private Dimension mySize;

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
        return getMyCenter();
    }
    
    /**
     * Return velocity of sprite
     */
    public Vector getVector () {
        return getMyVelocity();
    }

    /**
     * Update sprite location, size, velocity
     * @param center of sprite
     * @param size of sprite
     * @param velocity of sprite
     */
    public void update (Location center, Dimension size, Vector velocity) {
        setMyCenter(center);
        mySize = size;
        setMyVelocity(velocity);
    }


    public Location getMyCenter () {
        return myCenter;
    }


    public void setMyCenter (Location myCenter) {
        this.myCenter = myCenter;
    }


    public Vector getMyVelocity () {
        return myVelocity;
    }


    public void setMyVelocity (Vector myVelocity) {
        this.myVelocity = myVelocity;
    }

}
