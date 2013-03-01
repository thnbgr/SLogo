package parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public static void process(String command){
		Stack<String> stack = new Stack<String>();
		Map<String, String> validCommandRegex = new HashMap<String, String>();
		validCommandRegex.put("FORWARD", "(^\\s\\d+)");
		validCommandRegex.put("SUM", "(^\\s\\d+\\s\\d+)");
		//TODO: reflection
		
		String commandPattern = "(\\w+)";
		
		Pattern r = Pattern.compile(commandPattern);
		
		Matcher m = r.matcher(command);
		String lastCommandCall = "";
		int startIndex = 0;
		int endIndex = 0;
		while (m.find()){
			if (validCommandRegex.containsKey(m.group(1))){
				lastCommandCall = m.group(1);
				startIndex = m.start();
				endIndex = m.end();
				System.out.println("YAY! " + m.group(1));
			}
		}
		if (lastCommandCall.equals("")){
			System.out.println("NO MATCHES!!");
		}else{
			String commandPattern2 = validCommandRegex.get(lastCommandCall);
			Pattern r2 = Pattern.compile(commandPattern2);
			Matcher m2 = r2.matcher(command).region(endIndex, command.length());
			if (m2.find()){
				System.out.println(m2.group(1));
				StringBuffer simplifiedCommand = new StringBuffer();
				//TODO: replace command, recursion
			}else{
				System.out.println("NO MATCHES!!!");
			}
		}
		
	}
	private void makeTrees(String command, int repeatCounter){
		try {
			myEncodeTrees.add(myParser.encode(command));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public static void main(String args[]) {
		int commandCount = 10;
		try {
			while(commandCount > 0){
				String s = readUserInput("enter command: ");
				process(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
