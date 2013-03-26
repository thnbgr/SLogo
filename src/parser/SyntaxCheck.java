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
	
	private Map<String, String> validCommandRegex = new HashMap<String, String>();
	private String lastCommandCall = "";
	private int commandCallStartIndex = 0;
	private int commandCallEndIndex = 0;
	
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
				validCommandRegex.put(individualCommandRegex[0],individualCommandRegex[1]);
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
		findLastCommand(command);
		
		if (lastCommandCall.equals("")){
			return false;
		}
		
		String commandPattern = validCommandRegex.get(lastCommandCall);
		Pattern r = Pattern.compile(commandPattern);
		Matcher m = r.matcher(command).region(commandCallEndIndex, command.length());
		if (m.find()){
			//System.out.println(m2.group(1));
			int endIndex = m.end();
			String simplifiedCommand = command.substring(0, commandCallStartIndex) + "0" + command.substring(endIndex);
			//System.out.println(simplifiedCommand);
			return syntaxCheck(simplifiedCommand);
		}else{
			return false;
		}
	}

	/**
	 * Finds the last Command or Variable name in the input string.
	 * @param command entire user input string
	 */
	private void findLastCommand (String command){
		String commandPattern = "(\\w+|\\:)";
		
		Pattern r = Pattern.compile(commandPattern);
		
		Matcher m = r.matcher(command);
		while (m.find()){
			if (validCommandRegex.containsKey(m.group(1))){
				lastCommandCall = m.group(1);
				commandCallStartIndex = m.start();
				commandCallEndIndex = m.end();
				//System.out.println("YAY! " + m.group(1));
			}
		}
	}
	
	/**
	 * Finds the first valid command composed of the given String array. Start
	 * from the given index.
	 * @throws IOException 
	 * 
	 */
	public String findFirstValidCommand(String[] commandComponents, int index) throws IOException{
		String validParameter = commandComponents[index];
		int number = 1;
		while (!syntaxCheck(validParameter)){
			validParameter = validParameter + " " + commandComponents[index + number];
			number += 1;
		}
		return validParameter;
	}

	/**
     * Identifies the multiple commands separated by space in a single input.
     * @return
	 * @throws IOException 
     */
    public ArrayList<String> splitMultipleCommands(String command) throws IOException{
    	ArrayList<String> splitedCommands = new ArrayList<String>();
    	Queue<String> myCommandComponents = new LinkedList<String>();
		myCommandComponents.addAll(Arrays.asList(command.split(" ")));
    	
		while (!myCommandComponents.isEmpty()){
			String singleCommand = myCommandComponents.remove();
			if (!validCommandRegex.containsKey(singleCommand)){
				splitedCommands.add(singleCommand);
			}else{
				while (!syntaxCheck(singleCommand)){
					singleCommand += " ";
					singleCommand += myCommandComponents.remove();
				}
				splitedCommands.add(singleCommand);
			}
		}
    	return splitedCommands;
    }
    
    public StructureInfoPackage splitControlStructure(String controlName, String command) throws IOException{
    	int firstBraceIndex = command.indexOf('[');
    	String controlValue = command.substring(controlName.length()+1, firstBraceIndex-1);
    	ArrayList<ArrayList<String>> childCommands = new ArrayList<ArrayList<String>>();
    	splitControlStructureHelper(command, childCommands);
    	return new StructureInfoPackage(controlName, controlValue, childCommands);
    }
    
    public void splitControlStructureHelper(String command, ArrayList<ArrayList<String>> childCommands) throws IOException{
    	if (command.endsWith("[ 0 ]")){
    		command = command.substring(0, command.length()-6);
    	}

    	String commandPattern = "(\\[)";
		
		Pattern r = Pattern.compile(commandPattern);
		
		Matcher m = r.matcher(command);
		int leftBracketStartIndex = -1;
		while (m.find()){
			leftBracketStartIndex = m.start();
		}
		if (leftBracketStartIndex == -1){
			return;
		}
		
		ArrayList<String> childCommand = splitMultipleCommands(command.substring(leftBracketStartIndex+2, command.length()-2));
		childCommands.add(0, childCommand);
		command = command.substring(0, leftBracketStartIndex + 2) + "0 ]";
		splitControlStructureHelper(command, childCommands);
    }
    

	public void updateValidSyntax(String commandName, String commandRegex) {
		validCommandRegex.put(commandName, commandRegex);
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
	public static void main(String args[]) {
		int commandCount = 10;
		SyntaxCheck sc = new SyntaxCheck();
		//readFile(COMMAND_REGEXS_FILE_NAME);
		try {
			while(commandCount > 0){
				String s = readUserInput("enter command: ");
				sc.splitControlStructure("ifelse", s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
