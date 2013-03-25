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
			System.out.println("valid command!!!!");
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
    
    //TODO: can actually use syntax check until ending becomes [0] and [0][0]
    //TODO: can actually make in one class (check endwith [0] until there's nothing)
	
    /**
     * Splits a valid REPEAT structure into its components.
     * @param command
     * @return
     * @throws IOException
     */
    public StructureInfoPackage splitRepeatStructure(String command) throws IOException{
    	String commandPattern = "(\\[)";
		
		Pattern r = Pattern.compile(commandPattern);
		
		Matcher m = r.matcher(command);
		int leftBracketStartIndex = -1;
		while (m.find()){
			leftBracketStartIndex = m.start();
		}
		String repeatValue = command.substring(7, leftBracketStartIndex-1);
		String repeatTrueCommand = command.substring(leftBracketStartIndex+2, command.length()-2);
		ArrayList<ArrayList<String>> repeatCommands = new ArrayList<ArrayList<String>>();
		ArrayList<String> splitedTrueCommands = splitMultipleCommands(repeatTrueCommand);
		repeatCommands.add(splitedTrueCommands);
		return new StructureInfoPackage("repeat", repeatValue, repeatCommands);
    }
    
    /**
     * Splits a valid IF structure into its components.
     * @param command
     * @return
     * @throws IOException
     */
    public StructureInfoPackage splitIfStructure(String command) throws IOException{
    	String commandPattern = "(\\[)";
		
		Pattern r = Pattern.compile(commandPattern);
		
		Matcher m = r.matcher(command);
		int leftBracketStartIndex = -1;
		while (m.find()){
			leftBracketStartIndex = m.start();
		}
		String ifValue = command.substring(3, leftBracketStartIndex-1);
		String ifTrueCommand = command.substring(leftBracketStartIndex+2, command.length()-2);
		ArrayList<ArrayList<String>> ifCommands = new ArrayList<ArrayList<String>>();
		ArrayList<String> splitedTrueCommands = splitMultipleCommands(ifTrueCommand);
		ifCommands.add(splitedTrueCommands);
		return new StructureInfoPackage("if", ifValue, ifCommands);
    }
    
    /**
     * Splits a valid IFELSE structure into its components.
     * @param command
     * @return
     * @throws IOException
     */
    public StructureInfoPackage splitIfElseStructure(String command) throws IOException{
    	String commandPattern = "(\\[)";
		
		Pattern r = Pattern.compile(commandPattern);
		
		Matcher m = r.matcher(command);
		int trueLeftBracketStartIndex = -1;
		int falseLeftBracketStartIndex = -1;
		
		while (m.find()){
			trueLeftBracketStartIndex = falseLeftBracketStartIndex;
			falseLeftBracketStartIndex = m.start();
		}
		String ifElseValue = command.substring(7, trueLeftBracketStartIndex-1);
		String ifElseTrueCommand = command.substring(trueLeftBracketStartIndex+2, falseLeftBracketStartIndex-3);
		String ifElseFalseCommand = command.substring(falseLeftBracketStartIndex+2, command.length()-2);
		
		ArrayList<ArrayList<String>> ifElseCommands = new ArrayList<ArrayList<String>>();
		ArrayList<String> splitedTrueCommands = splitMultipleCommands(ifElseTrueCommand);
		ArrayList<String> splitedFalseCommands = splitMultipleCommands(ifElseFalseCommand);
		
		ifElseCommands.add(splitedTrueCommands);
		ifElseCommands.add(splitedFalseCommands);
		return new StructureInfoPackage("ifelse", ifElseValue, ifElseCommands);
    }
    
    /**
     * Splits a valid TO structure into its components.
     * @param command
     * @return
     * @throws IOException
     */
    public StructureInfoPackage splitToStructure(String command) throws IOException{
    	String commandPattern = "(\\[)";
		
		Pattern r = Pattern.compile(commandPattern);
		
		Matcher m = r.matcher(command);
		int parameterLeftBracketStartIndex = -1;
		int commandLeftBracketStartIndex = -1;
		
		while (m.find()){
			parameterLeftBracketStartIndex = commandLeftBracketStartIndex;
			commandLeftBracketStartIndex = m.start();
		}
		String toValue = command.substring(3, parameterLeftBracketStartIndex-1);
		String toParameterCommand = command.substring(parameterLeftBracketStartIndex+2, commandLeftBracketStartIndex-3);
		String toCommandsCommand = command.substring(commandLeftBracketStartIndex+2, command.length()-2);
		
		ArrayList<ArrayList<String>> toCommands = new ArrayList<ArrayList<String>>();
		ArrayList<String> splitedParameterCommands = splitMultipleCommands(toParameterCommand);
		ArrayList<String> splitedCommandsCommands = splitMultipleCommands(toCommandsCommand);
		
		toCommands.add(splitedParameterCommands);
		toCommands.add(splitedCommandsCommands);
		return new StructureInfoPackage("to", toValue, toCommands);
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
				sc.syntaxCheck(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
