package parser;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Stack;

import parser.node.Node;

import command.CommandBundle;
/*
 * @author Junho Oh
 */
public class PreParser {
	private ArrayList<Node> myVariables;
	private ArrayList<EncodeTree> myEncodeTrees;
	private Parser myParser;
	
	public PreParser(){
		myParser = new Parser();
		myVariables = new ArrayList<Node>();
		myEncodeTrees = new ArrayList<EncodeTree>();
	}
	public ArrayList<EncodeTree> getEncodeTrees(){
		return myEncodeTrees;
	}
	
	public void process(String command){
		Stack<String> stack = new Stack<String>();
		//regex party 
		
	}
	private void makeTrees(String command, int repeatCounter){
		try {
			myEncodeTrees.add(myParser.encode(command));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
