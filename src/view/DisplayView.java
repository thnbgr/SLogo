package view;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JComponent;
import util.Drawable;
import util.Movable;
import util.Processable;
import util.Sprite;
import util.Turtle;

/**
 * DisplayView shows the turtle
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class DisplayView extends JComponent implements IView {
    
    private static final long serialVersionUID = 1L;
    private List<Drawable> myDrawables;
    private Turtle myTurtle;
    private int assignID;

    /**
     * Sets the size of the display view
     * @param size is the size of the display
     */
    public DisplayView(Dimension size) {
        setPreferredSize(size);
        setSize(size);
        assignID = 0;
        myDrawables = new ArrayList<Drawable>();
    }

    private void setDrawableID (Drawable d) {
        d.setID(assignID);
        assignID++;
    }

    
    /**
     * Sets the size of the display view
    * @param pen used to paint shape on the screen
     */
    @Override
    public void paint (Graphics pen) {
        pen.setColor(Color.WHITE);
        for (Drawable d : myDrawables) {
            d.paint();
        }
    }
    
    @Override
    public void paint () {
        
    }
    
    /**
     * Probably move this over to a DisplayModel later
     */
    public void addSprite (Sprite s) {
        setDrawableID(s);
        myDrawables.add(s);
    }
    
    public void addTurtle () {
        myTurtle = new Turtle();
    }
    
    public Turtle getTurtle() {
        return myTurtle;
    }


    public void updateMovable (Processable p) {
        Movable m = (Movable) getDrawableByID(p.getID());
        m.updateWithProcessable(p);
    }

    public Processable getProcessableByID (int id) {
        Movable m = (Movable) getDrawableByID(id);
        return m.extractProcessable();
    }

    public Drawable getDrawableByID (int i) {
        for (Drawable d : myDrawables) {
            if (d.getID() == i) return d;
        }
        return null;
    }

    public List<Drawable> getDrawables () {
        return myDrawables;
    }

    public void clear () {
        myDrawables = new ArrayList<Drawable>();
        assignID = 0;
    }


}
