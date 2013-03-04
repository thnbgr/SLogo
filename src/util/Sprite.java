package util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Random;


public class Sprite extends Drawable implements Movable {

    protected Location myCenter;
    protected Vector myVelocity;
    protected Pixmap myPixmap;
    protected Dimension mySize;
    protected Processable myProcessable;

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

    @Override
    public void createProcessable () {
        myProcessable = new Processable(myCenter, myVelocity, myID);
    }

    @Override
    public void updateWithProcessable (Processable p) {
        myCenter = p.getLocation();
        myVelocity = p.getVector();
    }

    @Override
    public Processable extractProcessable () {
        return myProcessable;
    }

}
