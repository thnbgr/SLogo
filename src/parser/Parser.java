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

/**
 * 
 * @author Junho Oh
 */
public class Parser {
	public static final String COMMAND_PROPERTIES_FILE_NAME = "commandProperties.csv";
	private CSVTable myCSVTable;
	private ArrayList<Node> myVariables;

	//have it get passed in 
	public Parser(){
		myCSVTable = new CSVTable(COMMAND_PROPERTIES_FILE_NAME);
		myVariables = new ArrayList<Node>();
	}
	public EncodeTree encode(String command) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException{
		command.toLowerCase();
		StringTokenizer st = new StringTokenizer(command);
		Queue<Node> myCurNodes = new LinkedList<Node>();
		while(st.hasMoreTokens()){
			String curValue = st.nextToken();
			if(curValue.equals("make")){
				makeParser(command);
			}
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
			
			//if(temp instanceof MakeNode) { myVariables.add(temp); }
		}
		Node curNode = myCurNodes.remove();
		curNode.makeTree(myCurNodes);
		EncodeTree returnTree = new EncodeTree(curNode);

		return returnTree;
	}
	public void makeParser(String command){
		try {
			String varName = command.substring(0,command.indexOf(" "));
			EncodeTree variableTree = encode(command.substring(command.indexOf(" ")+1));
			variableTree.evaluate();
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
	
	public ArrayList<Node> getVariables(){
		return myVariables;
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
