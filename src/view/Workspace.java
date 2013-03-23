package view;
import util.Turtle;
import java.util.ArrayList;

public class Workspace {
//this is the workspace class
	private ArrayList<Turtle> myTurtles;
	private ArrayList<Turtle> filteredTurtles;
	private String myCommand;
	
	public Workspace() {
		// TODO Auto-generated constructor stub
		myTurtles = new ArrayList<Turtle>();
	}

	public ArrayList<Turtle> getTurtles(){
		return filteredTurtles;
	}
	
	public void save(){
		//save to a resource bundle the necessary information required to relaunch the program. 
		
	}
	
	public void update() {
		
	}
	
}
