package util;

/**
 * Create Turtle
 * 
 * @author Natalia Carvalho
 * @author Eric Wu
 */
public class Turtle extends Sprite {

    private boolean myIsPenUp;

    public Turtle() {
        
        
    }
    
    public void penDown() {
        myIsPenUp = false;
    }
    
    public void penUp() {
        myIsPenUp = true;
    }
}
