package util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;


/**
 * This class represents an image on the screen and 
 * adds some utility functions to the Image class.
 * 
 * Note, Java only supports the formats: png, jpg, gif.
 * 
 * @author Robert C. Duvall
 */
public class Pixmap {
    // OS-independent relative resource locations (like URLs)
    private static final String RESOURCE_LOCATION = "/images/";
    // underlying implementation
    private java.awt.Image myImage;
    private String myFileName;


    /**
     * Create an image from the given filename.
     * @param fileName of image
     */
    public Pixmap (String fileName) {
        setImage(fileName);
    }

    /**
     * Create an image from a BufferedImage
     * @param image is image that will be set
     */
    public Pixmap (BufferedImage image) {
        myImage = image;
    }
    /**
     * Create a copy of image from the given other image.
     * @param other pixmap copy
     */
    public Pixmap (Pixmap other) {
        this(other.myFileName);
    }

    /**
     * Set this image to the image referred to by the given filename.
     * @param fileName image
     */
    public void setImage (String fileName) {
        myImage = new ImageIcon(getClass().getResource(RESOURCE_LOCATION + fileName)).getImage();
        myFileName = fileName;
    }

    /**
     * Describes how to draw the image on the screen.
     * @param pen is graphics pen
     * @param center is center of image
     * @param size of image
     */
    public void paint (Graphics2D pen, Point2D center, Dimension size) {
        paint(pen, center, size, 0);
    }

    /**
     * Describes how to draw the image rotated on the screen.
     * @param pen graphics pen
     * @param center is center of object
     * @param size is size of object
     * @param angle is to rotate by
     */
    public void paint (Graphics2D pen, Point2D center, Dimension size, double angle) {
        // save current state of the graphics area
        AffineTransform old = new AffineTransform(pen.getTransform());
        // move graphics area to center of this shape
        pen.translate(center.getX(), center.getY());
        // rotate area about this shape
        pen.rotate(angle);
        // draw as usual (i.e., rotated)
        pen.drawImage(myImage, -size.width / 2, -size.height / 2, size.width, size.height, null);
        // restore graphics area to its old state, so our changes have no lasting effects
        pen.setTransform(old);
    }
}
