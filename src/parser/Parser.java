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

/**
 * 
 * @author Junho Oh
 */
public class Parser {
	//probably shouldn't be static?
	public static final String COMMAND_PROPERTIES_FILE_NAME = "commandProperties.csv";
	private static CSVTable myCSVTable;

	static{
		myCSVTable = new CSVTable(COMMAND_PROPERTIES_FILE_NAME);
	}
	public static EncodeTree encode(CommandBundle myPackage) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException{
		//syntax check
		String command = myPackage.getStringCommand().toLowerCase();
		StringTokenizer st = new StringTokenizer(command);
		Queue<Node> myCurNodes = new LinkedList<Node>();
		while(st.hasMoreTokens()){
			String curValue = st.nextToken();
			Node temp = null;
			//TODO: how to check for whether it is a variable? or garbage?
			if(myCSVTable.returnCSVRow(curValue) == null){
				temp = new ValueNode(null,Double.parseDouble(curValue));
			}
			else{
				Class<?> headClass = Class.forName(myCSVTable.returnCSVRow(curValue).getCommandFilePath());
				//Class nodeClass = Class.forName(myNodeInit.get(myCSVTable.returnCSVRow(nodeType).getCommandNumArgs()));
				temp = (Node) headClass.getConstructors()[0].newInstance(new Node());
			}
			myCurNodes.add(temp);
		}
		EncodeTree returnTree = new EncodeTree(makeTree(myCurNodes.remove(), myCurNodes));

		return returnTree;
	}
	
	private static Node makeTree(Node token, Queue<Node> tokens){
		//polymorphism can remove this later
		if(token instanceof ValueNode){ return token; }
		else if(token instanceof BinaryNode){
			Node left = tokens.remove();
			left = makeTree(left, tokens);
			Node right = tokens.remove();
			right = makeTree(right, tokens);
			((BinaryNode)token).setChildren(left, right);
		}
		else if(token instanceof UnaryNode){
			Node child = tokens.remove();
			child = makeTree(child, tokens);
			((UnaryNode)token).setchild(child);
		}
		return token;
	}
	
	public static void decode(EncodeTree tree){
		Node head = tree.getHead();
		head.evaluate();
		System.out.println(head.getContainer().getValue());
	}
	private static String readUserInput(String printMessage) throws IOException{
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
		CommandBundle cb;
		int commandCount = 10;
		try {
			while(commandCount > 0){
				cb = new CommandBundle(readUserInput("enter command: "), null);
				et = Parser.encode(cb);
				Parser.decode(et);
				commandCount--;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	 * should probably not have a parser hierarchy....
	 * TODO: 
	 * 1. tokenize the string into operators and values
	 * 2. syntax check
	 * 3. build tree
	 * 
	 */
	
}
