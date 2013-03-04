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
	 * Does syntax checking before parsing. 
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
	 * Finds the last Command in the input string.
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
     * Identifies the multiple commands in a single input separated by space.
     * @return
	 * @throws IOException 
     */
    public ArrayList<String> splitMultipleCommands(String command) throws IOException{
    	ArrayList<String> splitedCommands = new ArrayList<String>();
    	Queue<String> myCommandComponents = new LinkedList<String>();
		myCommandComponents.addAll(Arrays.asList(command.split(" ")));
    	
		while (!myCommandComponents.isEmpty()){
			String singleCommand = myCommandComponents.remove();
			while (!syntaxCheck(singleCommand)){
				singleCommand += " ";
				singleCommand += myCommandComponents.remove();
			}
			splitedCommands.add(singleCommand);
		}
    	return splitedCommands;
    }
	
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
    
    public StructureInfoPackage splitIfElseStructure(String command) throws IOException{
    	String commandPattern = "(\\[)";
		
		Pattern r = Pattern.compile(commandPattern);
		
		Matcher m = r.matcher(command);
		int trueLeftBracketStartIndex = -1;
		int falseLeftBracketStartIndex = -1;
		
		while (m.find()){
			trueLeftBracketStartIndex = falseLeftBracketStartIndex;
			falseLeftBracketStartIndex = m.start();
			leftBracketStartIndex = m.start();
		}
		String ifValue = command.substring(3, leftBracketStartIndex-1);
		String ifTrueCommand = command.substring(leftBracketStartIndex+1, command.length()-2);
		ArrayList<ArrayList<String>> ifCommands = new ArrayList<ArrayList<String>>();
		ArrayList<String> splitedTrueCommands = splitMultipleCommands(ifTrueCommand);
		ifCommands.add(splitedTrueCommands);
		return new StructureInfoPackage(ifValue, ifTrueCommand, ifCommands);
    }
    
}
