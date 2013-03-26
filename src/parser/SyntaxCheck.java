package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class SyntaxCheck {
	
	public static final String COMMAND_REGEXS_FILE_NAME = "commandRegexs.csv";
	
	private Map<String, String> myValidCommandRegex = new HashMap<String, String>();
	private String myLastCommandCall = "";
	private int myCommandCallStartIndex = 0;
	private int myCommandCallEndIndex = 0;
	private SyntaxSpliter mySyntaxSpliter = new SyntaxSpliter();
	
	public SyntaxCheck (){
		readFile(COMMAND_REGEXS_FILE_NAME);
	}
	
    /**
	 * Reads file from disk.
	 * @param fileName file to read
	 */
	private void readFile(String fileName){
		try {
			BufferedReader CSVFile = new BufferedReader(new FileReader(fileName));
			String individualLine = CSVFile.readLine();
			while (individualLine != null){
				String[] individualCommandRegex = individualLine.split(",");
				myValidCommandRegex.put(individualCommandRegex[0],individualCommandRegex[1]);
				individualLine = CSVFile.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks for syntax validity before parsing. 
	 * Check doesn't include: invalid values and variables.
	 * @param command the entire user input string
	 * @return boolean that represents whether the input string is valid
	 * @throws IOException 
	 */
	public boolean syntaxCheck(String command) throws IOException{
		if (command.equals("0")){
			return true;
		}
		syntaxCheckHelper(command);
		
		if (myLastCommandCall.equals("")){
			return false;
		}
		
		String commandPattern = myValidCommandRegex.get(myLastCommandCall);
		Pattern r = Pattern.compile(commandPattern);
		Matcher m = r.matcher(command).region(myCommandCallEndIndex, command.length());
		if (m.find()){
			int endIndex = m.end();
			String simplifiedCommand = command.substring(0, myCommandCallStartIndex) + "0" + command.substring(endIndex);
			return syntaxCheck(simplifiedCommand);
		}else{
			return false;
		}
	}

	/**
	 * Finds the last Command or Variable name in the input string.
	 * @param command entire user input string
	 */
	private void syntaxCheckHelper (String command){
		String commandPattern = "(\\w+|\\:)";
		
		Pattern r = Pattern.compile(commandPattern);
		
		Matcher m = r.matcher(command);
		while (m.find()){
			if (myValidCommandRegex.containsKey(m.group(1))){
				myLastCommandCall = m.group(1);
				myCommandCallStartIndex = m.start();
				myCommandCallEndIndex = m.end();
			}
		}
	}
	
	public void updateValidSyntax(String commandName, String commandRegex) {
		myValidCommandRegex.put(commandName, commandRegex);
	}
	
	public Map<String, String> getValidSyntax(){
		return myValidCommandRegex;
	}
    
    /**
	 * Testing purpose.
	 */
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
	
    /**
	 * Testing purpose.
	 */
	private static void main(String args[]) {
		int commandCount = 10;
		SyntaxCheck sc = new SyntaxCheck();
		try {
			while(commandCount > 0){
				String s = readUserInput("enter command: ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
