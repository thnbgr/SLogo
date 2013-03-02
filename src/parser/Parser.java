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

	public static final String COMMAND_PROPERTIES_FILE_NAME = "commandProperties.csv";
	private CSVTable myCSVTable;

	public Parser(){
		myCSVTable = new CSVTable(COMMAND_PROPERTIES_FILE_NAME);
	}
	public EncodeTree encode(String command) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException{
		//syntax check
		command.toLowerCase();
		StringTokenizer st = new StringTokenizer(command);
		Queue<Node> myCurNodes = new LinkedList<Node>();
		while(st.hasMoreTokens()){
			String curValue = st.nextToken();
			Node temp = null;
			if(myCSVTable.returnCSVRow(curValue) == null){
				temp = new ValueNode(null,Double.parseDouble(curValue));
			}
			else{
				Class<?> headClass = Class.forName(myCSVTable.returnCSVRow(curValue).getCommandFilePath());
				temp = (Node) headClass.getConstructors()[0].newInstance(new Node());
			}
			myCurNodes.add(temp);
		}
		EncodeTree returnTree = new EncodeTree(makeTree(myCurNodes.remove(), myCurNodes));

		return returnTree;
	}
	
	private Node makeTree(Node token, Queue<Node> tokens){
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
	
	public void decode(EncodeTree tree){
		Node head = tree.getHead();
		head.evaluate();
		System.out.println(head.getContainer().getValue());
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
