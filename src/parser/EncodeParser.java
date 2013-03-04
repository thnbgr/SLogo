package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Queue;

import java.util.StringTokenizer;
import parser.node.*;
import java.util.ArrayList;

/**
 * 
 * @author Junho Oh
 */
public class EncodeParser {
	public static final String COMMAND_PROPERTIES_FILE_NAME = "src/commandProperties.csv";
	private CSVTable myCSVTable;
	private ArrayList<Node> myVariables;

	public EncodeParser() {
		myCSVTable = new CSVTable(COMMAND_PROPERTIES_FILE_NAME);
		myVariables = new ArrayList<Node>();
	}
	public EncodeTree encode(String command) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException{
		//syntax check
		command.toLowerCase();
		command = command.replace("[", "");
		command = command.replace("]", "");
		
		StringTokenizer st = new StringTokenizer(command);
		Queue<Node> myCurNodes = new LinkedList<Node>();
		while(st.hasMoreTokens()){
			String curValue = st.nextToken();
			
			//curValue = "make" 
			//, st.nextToken(); 
			Node temp = null;
			if(myCSVTable.returnCSVRow(curValue) == null){
				temp = new ValueNode(Double.parseDouble(curValue));
			}
			else{
				Class<?> headClass = Class.forName(myCSVTable.returnCSVRow(curValue).getCommandFilePath());
				temp = (Node) headClass.getConstructors()[0].newInstance();
			}
			myCurNodes.add(temp);
			
			//if(temp instanceof MakeNode) { myVariables.add(temp); }
		}
		Node curNode = myCurNodes.remove();
		curNode.makeTree(myCurNodes);
		EncodeTree returnTree = new EncodeTree(curNode);

		return returnTree;
	}
	
	//put in model already. Didn't delete for testing purpose.
	public void decode(EncodeTree et){
		Node head = et.getHead();
		for(int i = 0; i < 5;i++){
			head.evaluate();
			
			System.out.println(head.getContainer().getValue());
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
		EncodeParser p = new EncodeParser();
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
