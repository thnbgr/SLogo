package view;
import util.Turtle;
import java.util.ArrayList;
import java.util.HashMap;

public class Workspace {
//this is the workspace class
	//private HashMap<Integer, TurtleData> mySTH = new HashMap<Integer, TurtleData>();
	//COMMENT: would this be better? the View will pass us this HashMap and the workspace ID
				//- what Jimmy was talking about passing a collection of data
	//Integer is the turtle ID, TurtleData is a package that contains the part of the turtle that we can change
	//the list of booleans for viewCommands we were talking about can also be included.
	//and updaing would also be easier? we can just update the values of TurtleData.
	
	//I think Workspace won't really need to store its customCommands,
	//since controller is the one who's actually dealing with the commands,
	//we can just store a map in controller?
	//and that's what the Workspace ID passed by the view is for,
	//so that we will know which workspace it is.
	
	//I was thinking about this way of implementation cuz
	//1. interface proved to be awkward (as said in gchat, it's meant for
	//new functionalities, not changing old ones, and it will actually require
	//some classes (other than Turtle, or Strategy would be pointless) to implement it
	//and those classes to be used in Turtle (even though itself is treated 
	//as a variable))
	//2. I was thinking of alternatives of passing around Turtles.
	
	private ArrayList<Turtle> myTurtles;
	private ArrayList<Turtle> filteredTurtles;
	private Turtle ryan; //for testing
	private String myCommand;
	private HashMap<Integer, ArrayList<String>> myViewCommands = new HashMap<Integer, ArrayList<String>>();
	
	public Workspace() {
		myTurtles = new ArrayList<Turtle>();
		ryan = new Turtle();
	}

	public ArrayList<Turtle> getTurtles(){
		return filteredTurtles;
	}
	public Turtle getTurtle(){
		return ryan;
	}
	public String getCommand(){
		return myCommand;
	}
	public void save(){
		//save to a resource bundle the necessary information required to relaunch the program. 	
	}
	
	public void update(String s) { //update to all Turtles
		
	}
	
	public void update(String s, ArrayList<Turtle> turtleList){ //update to specific turtles
		
	}
	
}
