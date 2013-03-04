package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;


public class Line extends JComponent {

    private Location myStartLocation;
    private Location myEndLocation;
    private final Color myColor;
    
    public Line (Location startLocation, Location endLocation, Color color) {
        myStartLocation = startLocation;
        myEndLocation = endLocation;
        myColor = color;
    }
    
    public void paint (Graphics g) {
        super.paintComponent(g);
        g.setColor(myColor);
        g.drawLine((int)myStartLocation.x, (int)myStartLocation.y, 
                   (int)myEndLocation.x, (int)myEndLocation.y);
    }

}
