package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import controller.MainController;
import util.Colors;
import util.Line;
import util.Location;
import util.Pixmap;
import util.Turtle;

/**
 * DisplayView shows the turtle
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class DisplayView extends JComponent {
    // better way to think about timed events (in milliseconds)
    /** */
    public static final int ONE_SECOND = 1000;    
    /** */
    public static final int FRAMES_PER_SECOND = 25;
    /** */
    public static final int DEFAULT_DELAY = ONE_SECOND / FRAMES_PER_SECOND;   
    /** */
    public static final int TURTLE_SIZE = 50;
    
    private static final long serialVersionUID = 1L;
    private static Dimension ourDefaultTurtleSize = new Dimension(TURTLE_SIZE, TURTLE_SIZE);
    private static Location ourDefaultTurtleLocation;
    private List<Turtle> myTurtles;
    private Turtle myTurtle;
    private int myAssignID;
    private Colors myColors;
    private int myShapeIndex;
    private List<Pixmap> myShapes;
    public List<Turtle> myStamps;
    boolean gridEnabled;
    // drives the animation
    private Timer myTimer;
    private JFrame myJFrame;

    /**
     * Sets the size of the display view
     * @param size is the size of the display
     */
    public DisplayView(Dimension size) {
        ourDefaultTurtleLocation = new Location(size.getHeight() / 2, size.getWidth() / 2);
        setPreferredSize(size);
        setSize(size);
        myAssignID = 0;
        myTurtles = new ArrayList<Turtle>();
        myColors = new Colors();
        myShapes = new ArrayList<Pixmap>();
        myStamps = new ArrayList<Turtle>();
        myShapeIndex = 0;
        gridEnabled = false;
        setShapeIndex(Turtle.DEFAULT_IMAGE);
        setVisible(true);
        
    }

    private void setTurtlesID (Turtle d) {
        d.setID(myAssignID);
        myAssignID++;
    }

    public int setShapeIndex (Pixmap p) {
        p.setIndex(myShapeIndex);
        myShapes.add(myShapeIndex, p);
        myShapeIndex++;
        return myShapeIndex-1;
    }
    
    public int getIndexByPixmap (Pixmap p) {
        return p.getIndex();
    }
    
    public Pixmap getPixmapByIndex (int shapeIndex) {
        return myShapes.get(shapeIndex);
    }
    
    public void createStamp (Turtle t) {
        Turtle stamp = (Turtle) t.stamp();
        myStamps.add(stamp);
    }
    
    public void clearStamps () {
        myStamps.clear();
    }
    
    /**
     * Returns myColors
     */
    public Colors getColors() {
        return myColors;
    }
    
    /**
     * Updates turtle colors
     */
    public void updateTurtleColors() {
        for (Turtle d : myTurtles) {
            d.setColors(myColors);
        }
        
    }
    
    
    /**
     * Sets the size of the display view
    * @param pen used to paint shape on the screen
     */
    @Override
    public void paint (Graphics pen) {
        pen.setColor(Color.WHITE);
        if (gridEnabled) paintGrid(pen);
        for (Turtle d : myTurtles) {
            d.paint((Graphics2D) pen);
        }
        for (Turtle t : myStamps) {
            if (t != null) {
            t.paint((Graphics2D) pen);
            }
        }
        
    }
    
    public void paintGrid (Graphics pen) {
        pen.setColor(Color.GRAY);
        int width = MainController.DISPLAY_VIEW_SIZE.width;
        int height = MainController.DISPLAY_VIEW_SIZE.height;
        int increment = 20;
        for (int i=0;i<width;i+=increment) {
            new Line(new Location(i, 0), new Location(i, height), myColors.getLineColor()).paint(pen);
        }
        for (int i=0;i<height;i+=increment) {
            new Line(new Location(0, i), new Location(width, i), myColors.getLineColor()).paint(pen);
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
        myTimer.start();
    }
    
    /**
     * Take one step in the animation.
     */
    public void step () {
        repaint();
    }

    /**
     * Stop the animation.
     */
    public void stop () {
        myTimer.stop();
    }
    

    /**
     * Add turtle to myTurtles
     */
    public void addTurtle () {
        myTurtle = new Turtle(ourDefaultTurtleLocation, ourDefaultTurtleSize, myColors);
        setTurtlesID(myTurtle);
        myTurtles.add(myTurtle);
    }
    
    /**
     * Add turtle to myTurtles
     * @param t is turtle t
     */
    public void addTurtle (Turtle t) {
        setTurtlesID(t);
        myTurtles.add(t);
    }
    
    /**
     * Add turtle to myDrawables
     */
    public Turtle getTurtle() {
        return myTurtles.get(0);
    }
    
    /**
     * Returns true if there is a turtle
     */
    public boolean hasTurtle() {
        return myTurtles.size() == 0;
    }

    /**
     * get a drawable given it's ID
     * @param i is ID of drawable
     */
    public Turtle getTurtleByID (int i) {
        for (Turtle d : myTurtles) {
            if (d.getID() == i) {
                return d;
            }
        }
        return null;
    }

    /**
     * return myDrawables
     */
    public List<Turtle> getTurtles () {
        return myTurtles;
    }

    /**
     * clear myTurtles
     */
    public void clear () {
        myTurtles = new ArrayList<Turtle>();
        myAssignID = 0;
    }

    /**
     * add JFrame
     * @param frame is frame to be added
     */
    public void addFrame (JFrame frame) {
        myJFrame = frame;
        
    }
    
    /**
     * add get JFrame
     */
    public JFrame getFrame () {
        return myJFrame;
    }

    public void setTurtleCommandable (int id) {
        // TODO Auto-generated method stub
        
    }

    public int getIDsAssigned () {
        return myAssignID;
    }

    public void setGrid (int enable) {
        gridEnabled = (enable == 1);
    }



    

}
