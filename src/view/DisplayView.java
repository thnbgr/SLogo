package view;

import java.util.ArrayList;
import java.util.List;
import util.Drawable;
import util.Movable;
import util.Processable;
import util.Sprite;
import util.Turtle;


public class DisplayView implements IView {

    private List<Drawable> myDrawables;
    private int assignID;

    public DisplayView () {
        assignID = 0;
        myDrawables = new ArrayList<Drawable>();
    }

    private void setDrawableID (Drawable d) {
        d.setID(assignID);
        assignID++;
    }

    public void updateMovable (Processable p) {
        Movable m = (Movable) getDrawableByID(p.getID());
        m.updateWithProcessable(p);
    }

    public Processable getProcessableByID (int id) {
        Movable m = (Movable) getDrawableByID(id);
        return m.extractProcessable();
    }

    private Drawable getDrawableByID (int i) {
        for (Drawable d : myDrawables) {
            if (d.getID() == i) return d;
        }
        return null;
    }

    public void paint () {

    }
}
