package parser;

import java.util.ArrayList;
/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class StructureInfoPackage {
	private String myType;
	private String myValue;
	private ArrayList<ArrayList<String>> myCommands;
	
	public StructureInfoPackage(String type, String value, ArrayList<ArrayList<String>> commands){
		myType = type;
		myValue = value;
		myCommands = commands;
	}
	public String getType(){
		return myType;
	}
	public String getValue(){
		return myValue;
	}
	public ArrayList<ArrayList<String>> getCommands(){
		return myCommands;
	}
}
