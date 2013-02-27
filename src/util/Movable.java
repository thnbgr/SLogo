package util;

public interface Movable {
    
    public void createProcessable();
    
    public Processable extractProcessable ();

    public abstract void updateWithProcessable (Processable p);
    
}
