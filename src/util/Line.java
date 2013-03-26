package util;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Create drawable line
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class Line extends JComponent {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Location myStartLocation;
    private Location myEndLocation;
    private final Color myColor;
    private boolean isVisible;
    
    /**
     * Constructor for line
     * @param end 
     * @param start 
     * @param startLocation for line
     * @param endLocation for line
     * @param color of line
     */
    
    public Line (Location start, Location end, Color lineColor) {
        setLocations(start, end);
        myColor = lineColor;
    }
    
    public void setLocations (Location startLocation, Location endLocation) {
        myStartLocation = startLocation;
        myEndLocation = endLocation;
    }
    
    

    /**
     * Paints the line
     * @param g is what paints the line
     */
    public void paint (Graphics g) {
        super.paintComponent(g);
        g.setColor(myColor);
        g.drawLine((int)myStartLocation.x, (int)myStartLocation.y, 
                   (int)myEndLocation.x, (int)myEndLocation.y);
    }

}
