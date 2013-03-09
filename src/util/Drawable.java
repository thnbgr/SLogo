package util;

import java.awt.Graphics2D;

/**
 * Create Drawable that is the superclass of every object that is drawn
 * THIS CLASS EVENTUALLY NEEDS TO BE DELETED, because right now it's useless
 * @author Natalia Carvalho and Eric Wu
 */
public abstract class Drawable {
    private boolean myIsVisible;
    protected int myID;

    public abstract void paint (Graphics2D pen);
    

    /**
     * Returns myIsVisible
     */
    public boolean isVisible () {
        return myIsVisible;
    }

    /**
     * Sets myIsVisible
     * @param isVisible is parameter myIsVisible needs to be set to
     */
    public void setVisible (boolean isVisible) {
        this.myIsVisible = isVisible;
    }

    /**
     * Returns myID
     */
    public int getID () {
        return myID;
    }

    /**
     * Set id for drawable
     * @param id is the ID to be set
     */
    public void setID (int id) {
        myID = id;
    }

}
