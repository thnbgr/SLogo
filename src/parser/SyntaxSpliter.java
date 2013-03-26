package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxSpliter {
	
	private SyntaxCheck mySyntaxCheck;
	
	/**
	 * Finds the first valid command composed of the given String array. Start
	 * from the given index.
	 * @throws IOException 
	 * 
	 */
	public String findFirstValidCommand(String[] commandComponents, int index) throws IOException{
		String validParameter = commandComponents[index];
		int number = 1;
		while (!mySyntaxCheck.syntaxCheck(validParameter)){
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
			if (!mySyntaxCheck.getValidSyntax().containsKey(singleCommand)){
				splitedCommands.add(singleCommand);
			}else{
				while (!mySyntaxCheck.syntaxCheck(singleCommand)){
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
    
    public void setSyntaxCheck (SyntaxCheck sc){
    	mySyntaxCheck = sc;
    }
}
