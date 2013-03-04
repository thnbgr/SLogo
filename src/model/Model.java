package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import parser.DecodeProcesser;
import parser.EncodeTree;
import parser.EncodeParser;
import parser.IfDecodeProcesser;
import parser.IfElseDecodeProcesser;
import parser.RepeatDecodeProcesser;
import parser.node.Node;
import view.DisplayView;

public class Model {
	private EncodeParser myParser;
	private Controller myController;
    private DecodeProcesser[] myDecodeProcessors = {new IfDecodeProcesser(this),
    		          new IfElseDecodeProcesser(this), new RepeatDecodeProcesser(this)};
    private String[] myKeywords = {"IF", "IFELSE", "REPEAT"};
	
	public Model(){
		myParser = new EncodeParser();
	}
	
	public void setController(Controller controller){
		myController = controller;
	}
	
	public Controller getController() {
		return myController;
	}
	
	
 

    public void encode (String c) {
      //  myProcessable = c.getProcessable();
    }


    public void update (double d) {
        // TODO Auto-generated method stub
        
    }
}
