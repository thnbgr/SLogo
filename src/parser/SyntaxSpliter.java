package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Junho Oh
 * @author Wenshun Liu
 *
 *			this class locates or splits commands from a string
 *			based on the syntax.
 */

public class SyntaxSpliter {

	private SyntaxCheck mySyntaxCheck;

	/**
	 * Finds the first valid command composed of the given String array.
	 * Start from the given index.
	 * @throws IOException
	 */
	public String findFirstValidCommand(String[] commandComponents,
					int index) throws IOException {
		String validParameter = commandComponents[index];
		int number = 1;
		while (!mySyntaxCheck.syntaxCheck(validParameter)) {
			validParameter = validParameter + " " +
					commandComponents[index + number];
			number += 1;
		}
		return validParameter;
	}

	/**
     * Identifies the multiple commands separated by space in a single input.
     * @return
     * @throws IOException
     */
    public ArrayList<String> splitMultipleCommands(String command)
    						throws IOException {
    	ArrayList<String> splitedCommands = new ArrayList<String>();
    	Queue<String> myCommandComponents = new LinkedList<String>();
		myCommandComponents.addAll(Arrays.asList(command.split(" ")));

		while (!myCommandComponents.isEmpty()) {
			String singleCommand = myCommandComponents.remove();
			if (!mySyntaxCheck.getValidSyntax().
					containsKey(singleCommand)) {
				System.out.println(singleCommand);
				splitedCommands.add(singleCommand);
			} else {
				while (!mySyntaxCheck.
						syntaxCheck(singleCommand)) {
					singleCommand += " ";
					singleCommand += myCommandComponents.remove();
				}
				splitedCommands.add(singleCommand);
			}
		}
    	return splitedCommands;
    }

    /**
     * Splits a valid control command (e.g. to, repeat, if, ifelse) into
     * its components.
     * @param controlName The name of the control structure. E.g. to
     * @param command The command to be split
     * @return S
     * @throws IOException
     */
    public StructureInfoPackage splitControlStructure(String controlName, boolean hasValue,
    					String command) throws IOException {
    	int firstBraceIndex = command.indexOf('[');
    	String controlValue = "";
    	if (hasValue){
    		controlValue = command.substring(controlName.length() + 1,
    				firstBraceIndex - 1);
    	}
    	ArrayList<ArrayList<String>> childCommands
    				= new ArrayList<ArrayList<String>>();
    	splitControlStructureHelper(command, childCommands);
    	return new StructureInfoPackage(controlName,
    						controlValue, childCommands);
    }

    /**
     * Helps splitControlStructure identify and split commands in "[ ]"s.
     * @param command
     * @param childCommands
     * @throws IOException
     */
    private void splitControlStructureHelper(String command,
    		ArrayList<ArrayList<String>> childCommands) throws IOException {
    	String tail = "[ 0 ]";
    	if (command.endsWith(tail)) {
    		command = command.substring(0,
    				command.length() - tail.length() - 1);
    	}

    	String commandPattern = "(\\[)";

		Pattern r = Pattern.compile(commandPattern);

		Matcher m = r.matcher(command);
		int leftBracketStartIndex = -1;
		while (m.find()) {
			leftBracketStartIndex = m.start();
		}
		if (leftBracketStartIndex == -1) {
			return;
		}

		ArrayList<String> childCommand = splitMultipleCommands(
				command.substring(leftBracketStartIndex + 2,
							command.length() - 2));
		childCommands.add(0, childCommand);
		command = command.substring(0,
					leftBracketStartIndex + 2) + "0 ]";
		splitControlStructureHelper(command, childCommands);
    }

    
    /**
     * Sets the SyntaxCheck.
     * @param sc The SyntaxCheck to be set.
     */
    public void setSyntaxCheck (SyntaxCheck sc) {
    	mySyntaxCheck = sc;
    }
    
    /**
	 * Testing purpose.
	 * @throws IOException
	 */
	private static String readUserInput(String printMessage)
						throws IOException {
        System.out.print(printMessage);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String returnString;
        try {
            returnString = br.readLine();
        } catch (IOException e) {
           throw new IOException(e);
        }
        return returnString;
    }

    /**
	 * Testing purpose.
	 * @param args[]
	 */
	public static void main(String args[]) {
		int commandCount = 1;
		SyntaxSpliter ss = new SyntaxSpliter();
		SyntaxCheck sc = new SyntaxCheck();
		ss.setSyntaxCheck(sc);
		try {
			while (commandCount > 0) {
				String s = readUserInput("enter command: ");
				ss.splitControlStructure("for", false, s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
