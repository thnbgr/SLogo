package parser;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import java.util.Stack;
import java.util.StringTokenizer;

import parser.node.*;
import parser.node.math.*;

import command.CommandBundle;

public class EncodeParser {
	private String testCommand = "sum 50 50";
	
	public static final String COMMAND_PROPERTIES_FILE_NAME = "commandProperties.csv";
	private CSVTable myCSVTable;
	private Stack<String> myValues;
	
	public EncodeParser(){
		myCSVTable = new CSVTable(COMMAND_PROPERTIES_FILE_NAME);
		myValues = new Stack<String>();
	}
	
	public void encode(CommandBundle myPackage) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException{
		//syntax check
		//tokenize
		StringTokenizer st = new StringTokenizer(myPackage.getStringCommand());
		
		while(st.hasMoreTokens()){
			String curValue = st.nextToken();
			System.out.println("cur value " + curValue);
			
			myValues.push(curValue);
		}
		
		//tree create
		//if(myCSVTable.isValidCommand(myValues.peek())){
		for(int i = 0; i < myValues.size();i++){
			String node1 = myValues.pop();
			String node2 = myValues.pop();
			String node3 = myValues.pop();
			System.out.println(node1 + node2 + node3);
			
			ValueNode left;
			ValueNode right;
			BinaryNode head;
			
			
			if(myCSVTable.returnNodeClassName(node1) == null){
				left = new ValueNode(null,Double.parseDouble(node1));
			}
			if(myCSVTable.returnNodeClassName(node2) == null){
				right = new ValueNode(null,Double.parseDouble(node1));
			}
			
			Class headClass = Class.forName("node.math." + myCSVTable.returnNodeClassName(node3));
			head = (BinaryNode) headClass.getConstructors()[0].newInstance(new Node());
			head.test();
			
			
		}
			
		//}
	}
	public static void main(String args[]) throws IllegalArgumentException, SecurityException, InvocationTargetException{
		EncodeParser ep = new EncodeParser();
		CommandBundle cb = new CommandBundle("sum 50 50", null);
		try {
			ep.encode(cb);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
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
