package util;

import java.awt.Dimension;
import java.util.Random;


public class Sprite extends Drawable implements Movable {

    private Location myLocation;
    private Vector myVector;
    private Pixmap myPixmap;
    private Dimension mySize;
    private Processable myProcessable;

    public Sprite () {

    }

    @Override
    public void paint () {
        // TODO Auto-generated method stub

    }

    public Location getLocation () {
        return myLocation;
    }

    public Vector getVector () {
        return myVector;
    }

    @Override
    public void createProcessable () {
        myProcessable = new Processable(myLocation, myVector, myID);
    }

    @Override
    public void updateWithProcessable (Processable p) {
        myLocation = p.getLocation();
        myVector = p.getVector();
    }

    @Override
    public Processable extractProcessable () {
        return myProcessable;
    }

}
