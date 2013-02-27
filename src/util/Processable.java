package util;

/**
 * Create interface Processable that contains data that needs to be passed
 * (ex: contains data that the turtle needs to contain)
 * 
 * @author Natalia Carvalho and Eric Wu
 */
public class Processable {

    Location myLocation;
    Vector myVector;
    int myID;

    public Processable (Location l, Vector v, int i) {
        myLocation = l;
        myVector = v;
        myID = i;
    }

    public Location getLocation () {
        return myLocation;
    };

    public Vector getVector () {
        return myVector;
    };

    public void setID (int i) {
        myID = i;
    };

    public int getID () {
        return myID;
    };

}
