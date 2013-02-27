package util;

public class Turtle extends Sprite {

    private boolean isPenUp;

    public Turtle() {
        
        
    }
    
    public void penDown() {
        isPenUp = false;
    }
    
    public void penUp() {
        isPenUp = true;
    }
}
