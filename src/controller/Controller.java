package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import EncodeParser.EncodeParser;
import EncodeParser.FDEncodeParser;
import command.CommandBundle;
import model.Model;
import view.IView;


public class Controller implements Observer {

    private List<IView> myViewList;
    private Model myModel;
    private Map<String, EncodeParser> myEncodeMap = new HashMap<String, EncodeParser>();
    
    public Controller (Model model) {
        myModel = model;
        myEncodeMap.put("fd", new FDEncodeParser());
    }

    public void addView (IView view) {
        myViewList.add(view);
    }

    @Override
    public void update (Observable o, Object a) { // why not just pass in CommandBundle??
        CommandBundle myPackage = (CommandBundle) a;
        String commandID = myPackage.getStringCommand().split(" ")[0];
        myEncodeMap.get(commandID).encode(myPackage);
        
        myModel.encode(myPackage);
    }

}
