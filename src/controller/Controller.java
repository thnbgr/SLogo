package controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import command.CommandBundle;
import model.Model;
import view.IView;


public class Controller implements Observer {

    private List<IView> myViewList;
    private Model myModel;

    public Controller (Model model) {
        myModel = model;
    }

    public void addView (IView view) {
        myViewList.add(view);
    }

    @Override
    public void update (Observable o, Object a) {
        CommandBundle myPackage = (CommandBundle) a;
        myModel.encode(myPackage);
    }

}
