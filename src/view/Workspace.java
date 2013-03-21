package view;
import util.Sprite;
import java.util.ArrayList;

public class Workspace {
//this is the workspace class
	private ArrayList<Sprite> mySprites;
	
	public Workspace() {
		// TODO Auto-generated constructor stub
		mySprites = new ArrayList<Sprite>();
	}

	public ArrayList<Sprite> getSprites(){
		return mySprites;
	}
	
	public void save(){
		//save to a resource bundle the necessary information required to relaunch the program. 
		
	}
	
	
}
