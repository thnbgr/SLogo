package util;

import java.awt.Graphics2D;


public class Line extends Drawable {

    private Location myStartLocation;
    private Location myEndLocation;
    
    public Line (Location startLocation, Location endLocation) {
        myStartLocation = startLocation;
        myEndLocation = endLocation;
    }

    @Override
    public void paint (Graphics2D pen) {
        // TODO Auto-generated method stub
        
    }

}
