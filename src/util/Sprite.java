package util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Random;


public class Sprite extends Drawable {

    protected Location myCenter;
    protected Vector myVelocity;
    protected Pixmap myPixmap;
    protected Dimension mySize;

    public Sprite () {

    }

    @Override
    public void paint (Graphics2D pen) {
        // TODO Auto-generated method stub

    }

    public void move (int distance) {
        myCenter.setLocation(distance+myCenter.getX(), myCenter.getY());
    }
    
    public void turn (int angle) {
        
    }
    
    public Location getLocation () {
        return myCenter;
    }

    public Vector getVector () {
        return myVelocity;
    }
    
    public void update (Location center, Dimension size, Vector velocity) {
        myCenter = center;
        mySize = size;
        myVelocity = velocity;
    }
}
