package parser;

import java.util.Stack;
import java.util.StringTokenizer;

public class Tokenizer {
	private Stack<Integer> myValues; 
	
	public Tokenizer() {
		myValues = new Stack<Integer>();
	}
	//need a file/enum containing all the valid 
	public void separate(String command){
		//
		StringTokenizer st = new StringTokenizer(command);
		
		while(st.hasMoreTokens()){
			String next = st.nextToken();
			if(myCSVTable.isValidCommand(next)){
				myOperators.push(next);
			}
			
		}
	}
	
}
