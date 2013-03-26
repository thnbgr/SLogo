package util;

import java.awt.geom.Point2D;


/**
 * This class represents a Location (in pixels) on the screen and 
 * adds some utility functions to the Point2D class.
 * 
 * Note, this might be overkill, it was just annoying that Point2D
 * did not implement translate :(
 * 
 * @author Robert C. Duvall
 */
public class Location extends Point2D.Double {
    // default serialization ID
    private static final long serialVersionUID = 1L;


    /**
     * Create a location at the origin.
     */
    public Location () {
        super(0, 0);
    }

    /**
     * Create a location at given (x, y) coordinates.
     * @param x location
     * @param y location
     */
    public Location (double x, double y) {
        super(x, y);
    }

    /**
     * Create a location that is identical to the given other location.
     * @param source location of other point
     */
    public Location (Point2D source) {
        super(source.getX(), source.getY());
    }

    /**
     * Reset this location to origin.
     */
    public void reset () {
        setLocation(0, 0);
    }

    /**
     * Move this location by given vector.
     * @return 
     * @see java.awt.Point#translate(int, int)
     * @param amount to be translated
     */
    public void translate (Vector amount) {
        setLocation(getX() + amount.getXChange(), getY() + amount.getYChange());
    }

    /**
     * Returns a vector that is the difference between this location and
     * the given other location.
     * @param other vector
     */
    public Vector difference (Point2D other) {
        return new Vector(this, other);
    }
    
    
}
