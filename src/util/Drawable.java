package util;

/**
 * Create Drawable that is the superclass of every object that is drawn
 * @author Natalia Carvalho
 */
public class Drawable {
    private boolean myIsVisible;
    
    
    public void paint() {
        
    }

    
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
    
}
