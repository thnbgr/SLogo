package parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.node.Node;
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

	private void makeTrees(String command, int repeatCounter){
		try {
			myEncodeTrees.add(myParser.encode(command));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
