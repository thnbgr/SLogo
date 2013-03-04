package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Queue;

import java.util.StringTokenizer;
import parser.node.*;
import command.CommandBundle;
import parser.node.turtleCommand.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;

/**
 * 
 * @author Junho Oh
 */
public class Parser {
	public static final String COMMAND_PROPERTIES_FILE_NAME = "commandProperties.csv";
	private CSVTable myCSVTable;
	private ArrayList<VariableNode> myVariables;
	private SyntaxCheck mySyntaxCheck;

	//have it get passed in 
	public Parser(){
		myCSVTable = new CSVTable(COMMAND_PROPERTIES_FILE_NAME);
		myVariables = new ArrayList<VariableNode>();
		mySyntaxCheck = new SyntaxCheck();
	}
	public EncodeTree encode(String command) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException{
		command.toLowerCase(); //remove later 
		Queue<String> myCommandParts = new LinkedList<String>();
		myCommandParts.addAll(Arrays.asList(command.split(" ")));
		Queue<Node> myCurNodes = new LinkedList<Node>();
		while(myCommandParts.size() > 0){
			String curValue = myCommandParts.remove();
			//todo 
			if(curValue.equals("make")){
				String makeCommand = curValue;
				while (!mySyntaxCheck.syntaxCheck(makeCommand)){
					makeCommand += " " + myCommandParts.remove();
				}
				makeParser(makeCommand);
			}
			if(curValue.equals("if")){
				String makeCommand = curValue;
				while (!mySyntaxCheck.syntaxCheck(makeCommand)){
					makeCommand += " " + myCommandParts.remove();
				}
			}
			else{
				Node temp = null;
				if(myCSVTable.returnCSVRow(curValue) == null){
					temp = new ValueNode(Integer.parseInt(curValue));
				}
				else{
					Class<?> headClass = Class.forName(myCSVTable.returnCSVRow(curValue).getCommandFilePath());
					temp = (Node) headClass.getConstructors()[0].newInstance();
					if(temp instanceof TurtleCommandNode){
						((TurtleCommandNode)temp).setName(curValue);
					}
					temp.setNumArgs(myCSVTable.returnCSVRow(curValue).getCommandNumArgs());
				}
				myCurNodes.add(temp);
			}

		}
		EncodeTree returnTree = new EncodeTree();
		if(!myCurNodes.isEmpty()){
			Node curNode = myCurNodes.remove();
			curNode.makeTree(myCurNodes);
			returnTree = new EncodeTree(curNode);
		}
		return returnTree;
	}
	public ArrayList<VariableNode> getVariables(){
		return myVariables;
	}
	public void makeParser(String command){
		try {
			String[] commandParts = command.split(" ");
			String varName = commandParts[1];
			String varValue = "";
			for(int i = 2; i < commandParts.length; i++){
				varValue += commandParts[i] + " ";
			}
			System.out.println(varValue);
			EncodeTree variableTree = encode(varValue);
			variableTree.getHead().evaluate();
			VariableNode aVariable = new VariableNode(variableTree.getHead().getValue(), varName);
			myVariables.add(aVariable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//put into model
	public void decode(EncodeTree tree){
		Node head = tree.getHead();
		head.evaluate();
		if(head instanceof TurtleCommandNode){
			System.out.println(((TurtleCommandNode) head).toString());
		}
		else{
			System.out.println(head.getValue());
		}
	}	
	private String readUserInput(String printMessage) throws IOException{
		System.out.print(printMessage);
		InputStreamReader isr = new InputStreamReader ( System.in );
		BufferedReader br = new BufferedReader (isr);
		String returnString;
		try {
			returnString=br.readLine();
		} catch (IOException e) {
			throw new IOException(e);
		}
		return returnString;
	}
	public static void main(String args[]) throws IllegalArgumentException, SecurityException, InvocationTargetException{
		EncodeTree et = new EncodeTree();
		Parser p = new Parser();
		String s;
		int commandCount = 10;
		try {
			while(commandCount > 0){
				s = p.readUserInput("enter command: ");
				et = p.encode(s);
				p.decode(et);
				commandCount--;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
