package view;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.Timer;
import model.Model;
import util.Drawable;
import util.Location;
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
    public static final int FRAMES_PER_SECOND = 25;
    // better way to think about timed events (in milliseconds)
    /** */
    public static final int ONE_SECOND = 1000;
    /** */
    public static final int DEFAULT_DELAY = ONE_SECOND / FRAMES_PER_SECOND;
    private static Dimension DEFAULT_TURTLE_SIZE = new Dimension(50, 50);
    private static Location DEFAULT_TURTLE_LOCATION = new Location();
    // drives the animation
    private Timer myTimer;
    // game to be animated
    private Model mySimulation;

    /**
     * Sets the size of the display view
     * @param size is the size of the display
     */
    public DisplayView(Dimension size) {
        setPreferredSize(size);
        setSize(size);
        assignID = 0;
        myDrawables = new ArrayList<Drawable>();
        setVisible(true);
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
            d.paint((Graphics2D) pen);
        }
        
    }
    
    /**
     * Start the animation.
     */
    public void start () {
        // create a timer to animate the canvas
        myTimer = new Timer(DEFAULT_DELAY, 
            new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    step();
                }
            });
        // start animation
        mySimulation = new Model();
        myTimer.start();
    }
    
    /**
     * Take one step in the animation.
     */
    public void step () {
        mySimulation.update((double)FRAMES_PER_SECOND / ONE_SECOND);
        // indirectly causes paint to be called
        repaint();
    }

    /**
     * Stop the animation.
     */
    public void stop () {
        myTimer.stop();
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
        myTurtle = new Turtle(DEFAULT_TURTLE_LOCATION, DEFAULT_TURTLE_SIZE);
        addSprite(myTurtle);
    }
    
    public Turtle getTurtle() {
        return myTurtle;
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
