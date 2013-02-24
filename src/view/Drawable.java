package view;

/**
 * Create Drawable that is the superclass of every object that is drawn
 * @author Natalia Carvalho
 */
public class Drawable {
    private boolean isVisible;
    
    
    private void paint() {
        
    }

    
    
    public boolean isVisible () {
        return isVisible;
    }

    public void setVisible (boolean isVisible) {
        this.isVisible = isVisible;
    }
    
}
