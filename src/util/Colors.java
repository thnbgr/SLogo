package util;
import java.awt.Color;


/**
 * Contains all colors
 * 
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class Colors {

    /**
     * color of line
     */
    
    private Color myLineColor;
    
    /**
     * Colors constructor
     * default line color is black
     */
    public Colors () {
        myLineColor = Color.black;
        
    }
    
    /**
     * Returns line color
     */
    public Color getLineColor() {
        return myLineColor;
    }
    
    /**
     * Sets line color
     * @param c color to be set
     */
    public void setLineColor(Color c) {
        myLineColor = c;
    }
}
