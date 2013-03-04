package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Queue;

import parser.node.*;
import parser.node.control.*;
import parser.node.turtleCommand.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Junho Oh
 * 
 * This class is responsible for taking in a command string and parsing it into a tree structure
 * for the model to be able to evaluate. 
 */
public class Parser {
	public static final String COMMAND_PROPERTIES_FILE_NAME = "commandProperties.csv";
	private CSVTable myCSVTable;
	private ArrayList<VariableNode> myVariables;
	private ArrayList<CustomCommandNode> myCustomCommands;
	private SyntaxCheck mySyntaxCheck;

	//have it get passed in 
	public Parser(String fileName){
		myCSVTable = new CSVTable(COMMAND_PROPERTIES_FILE_NAME);
		//For later use: myCSVTable =  new CSVTable(fileName);
		myVariables = new ArrayList<VariableNode>();
		myCustomCommands = new ArrayList<CustomCommandNode>();
		mySyntaxCheck = new SyntaxCheck();
	}
	public EncodeTree encode(String command) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException, IOException{
		command.toLowerCase(); //remove later 
		Queue<String> myCommandParts = new LinkedList<String>();
		myCommandParts.addAll(Arrays.asList(command.split(" ")));
		Queue<Node> myCurNodes = new LinkedList<Node>();
		Node curNode = new Node();
		
		while(myCommandParts.size() > 0){
			String curValue = myCommandParts.remove();
			//todo use reflection to remove these. 
			if(curValue.equals("make")){
				String makeCommand = curValue;
				while (!mySyntaxCheck.syntaxCheck(makeCommand)){
					makeCommand += " " + myCommandParts.remove();
				}
				makeParser(makeCommand);
			}
			else if(curValue.equals("if")){
				String ifCommand = curValue;
				while (!mySyntaxCheck.syntaxCheck(ifCommand)){ //does not work with if 4 [ fd 4 ]
					ifCommand += " " + myCommandParts.remove();
				}
				curNode = ifParser(ifCommand);
			}
			else if(curValue.equals("ifelse")){
				String ifElseCommand = curValue;
				while (!mySyntaxCheck.syntaxCheck(ifElseCommand)){
					ifElseCommand += " " + myCommandParts.remove();
				}
				curNode = ifElseParser(ifElseCommand);
			}
			else if(curValue.equals("repeat")){
				String repeatCommand = curValue;
				while (!mySyntaxCheck.syntaxCheck(repeatCommand)){
					repeatCommand += " " + myCommandParts.remove();
				}
				curNode = repeatParser(repeatCommand);
			}
			else if(curValue.equals("to")){
				String repeatCommand = curValue;
				while (!mySyntaxCheck.syntaxCheck(repeatCommand)){
					repeatCommand += " " + myCommandParts.remove();
				}
				curNode = repeatParser(repeatCommand);
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
			curNode = myCurNodes.remove();
			curNode.makeTree(myCurNodes);
		}
		returnTree = new EncodeTree(curNode);
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
	public Node ifParser(String command){
		try{
			StructureInfoPackage IfStructPackage = mySyntaxCheck.splitIfStructure(command);
			Node ifValueNode = encode(IfStructPackage.getValue()).getHead();
			Node ifCommands = new Node();
			for(String ifCommand : IfStructPackage.getCommands().get(0)){
				ifCommands.addChild(encode(ifCommand).getHead());
			}
			IfNode ifNode = new IfNode();
			ifNode.addChild(ifValueNode);
			ifNode.addChild(ifCommands);
			
			return ifNode;
		}
		catch(Exception e){
			
		}
		return null;
	}
	public Node ifElseParser(String command){
		try{
			StructureInfoPackage IfElseStructPackage = mySyntaxCheck.splitIfElseStructure(command);
			Node ifValueNode = encode(IfElseStructPackage.getValue()).getHead();
			Node ifCommands = new Node();
			for(String ifCommand : IfElseStructPackage.getCommands().get(0)){
				ifCommands.addChild(encode(ifCommand).getHead());
			}
			IfElseNode ifElseNode = new IfElseNode();
			ifElseNode.addChild(ifValueNode);
			ifElseNode.addChild(ifCommands);
			
			return ifElseNode;
		}
		catch(Exception e){
			
		}
		return null;
	}
	public Node toParser(String command){
		try{
			StructureInfoPackage toStructPackage = mySyntaxCheck.splitToStructure(command);
			String commandName = toStructPackage.getName();
			ArrayList<String> varNames = new ArrayList<String>();
			for(String name : toStructPackage.getVarNames()){
				varNames.add(commandName + name); //name mangling
			}
			String customCommand = toStructPackage.getCustCommand();
		}
		catch(Exception e){
			
		}
		return null;
	}
	public Node repeatParser(String command){
		try{
			StructureInfoPackage repeatStructPackage = mySyntaxCheck.splitRepeatStructure(command);
			Node repeatValueNode = encode(repeatStructPackage.getValue()).getHead();
			Node repeatCommands = new Node();
			for(String repeatCommand : repeatStructPackage.getCommands().get(0)){
				repeatCommands.addChild(encode(repeatCommand).getHead());
			}
			RepeatNode repeatNode = new RepeatNode();
			repeatNode.addChild(repeatValueNode);
			repeatNode.addChild(repeatCommands);
			
			return repeatNode;
		}
		catch(Exception e){
			
		}
		return null;
	}
	//This is only here for checking purposes
	//put into model
	public void ifDecode(Node ifNode){
		((IfNode)ifNode).getChildren().get(0).evaluate();
		if(ifNode.getChildren().get(0).getValue() != 0){
			for(Node ifCommand : ifNode.getChildren().get(1).getChildren()){
				decode(new EncodeTree(ifCommand));
			}
		}
	}
	public void ifElseDecode(Node ifElseNode){
		((IfElseNode)ifElseNode).getChildren().get(0).evaluate();
		if(ifElseNode.getChildren().get(0).getValue() != 0){
			for(Node ifCommand : ifElseNode.getChildren().get(2).getChildren()){
				decode(new EncodeTree(ifCommand));
			}
		}
		else if(ifElseNode.getChildren().get(0).getValue() == 0){
			for(Node ifCommand : ifElseNode.getChildren().get(1).getChildren()){
				decode(new EncodeTree(ifCommand));
			}
		}
	}
	public void decode(EncodeTree tree){
		Node head = tree.getHead();
		head.evaluate();
		if(head instanceof IfNode){
			ifDecode(head);
		}
		if(head instanceof IfElseNode){
			ifElseDecode(head);
		}
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
		Parser p = new Parser("");
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
