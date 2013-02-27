package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JComponent;
import util.Drawable;

/**
 * DisplayView shows the turtle
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class DisplayView extends JComponent implements IView {
    
    private static final long serialVersionUID = 1L;
    private List<Drawable> myDrawables;

    /**
     * Sets the size of the display view
     * @param size is the size of the display
     */
    public DisplayView(Dimension size) {
        setPreferredSize(size);
        setSize(size);
    }

    /**
     * Sets the size of the display view
    * @param pen used to paint shape on the screen
     */
    public void paint (Graphics pen) {
        pen.setColor(Color.WHITE);
        for (Drawable d : myDrawables) {
            d.paint();
        }
    }

    public void updateDrawables (Processable p) {

    }

    @Override
    public void paint () {        
    }

}
