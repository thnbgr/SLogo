package util;
import java.awt.Color;
import java.util.HashMap;


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
    private HashMap <Integer, Color> map;
    private int myLineColorIndex;
    
    /**
     * Colors constructor
     * default line color is black
     */
    public Colors () {
        myLineColor = Color.black;
        myLineColorIndex = -1; // no custom value
        map = new HashMap<Integer, Color>();
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
    
    public void addColor(int i, Color c) {
        map.put(i, c);
    }
    
    public Color getColorByIndex(int i) {
        if (map.get(i) == null) return null;
        return map.get(i);
    }
    
    public int getLineColorIndex() {
        return myLineColorIndex;
    }

    public void setLineColorByIndex (int colorIndex) {
        myLineColor = map.get(colorIndex);
        myLineColorIndex = colorIndex;
    }
}
