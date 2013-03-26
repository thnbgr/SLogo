package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import parser.node.VariableNode;
import parser.node.control.CustomCommandNode;

/**
*
* @author Junho Oh
* @author Wenshun Liu
*
* 		This class is responsible for finding all global variables in the
*		input string and replace them with their actual commands. This
*
*/

public class VariableParser extends AbstractParser {

	private CommandTreeParser myCommandTreeParser;

	public VariableParser(CommandTreeParser treeMakingParser) {
		myCommandTreeParser = treeMakingParser;
	}

	/**
	 * Loops through and finds the global variables in the command.
	 * Then replace them with values. 
	 */
	@Override
	public String parse(String command) throws IOException {
		String[] commandComponents = command.split(" ");
    	String preParsedCommand = "";
    	String current = "";
    	int i = 0;
    	while (i < commandComponents.length) {
    		String previous = current;
    		current = commandComponents[i];
    		if (current.equals("to")) {
    			String makeCommand = current;
				while (!myCommandTreeParser.getSyntaxCheck()
						.syntaxCheck(makeCommand)) {
					i += 1;
					makeCommand += " " + commandComponents[i];
				}
				StructureInfoPackage toPackage = myCommandTreeParser
						.getSyntaxSpliter()
						.splitControlStructure("to", true, makeCommand);

				preParsedCommand += structureParse(toPackage) + " ";
				i += 1;
    		} else if (current.startsWith(":") && !previous.equals("make")) {
    			 String preParsedVariable = preParseVariable(current);
    			preParsedCommand += preParsedVariable;
    			preParsedCommand += " ";
    			i += 1;
    		} else {
    			preParsedCommand += commandComponents[i] + " ";
    			i += 1;
    		}
    	}
    	return preParsedCommand.substring(0, preParsedCommand.length() - 1);
	}

	/**
	 * Finds the global variables in a structure command given the
	 * StructureInPackage of all the different components.
	 * @param siPackage
	 * @return The parsed String
	 */
	private String structureParse(StructureInfoPackage siPackage) {
		String result = "";
		result += siPackage.getType() + " ";
		result += siPackage.getValue() + " ";
		List<String> localVariables = new ArrayList<String>();
		result += "[ ";
		for (String s: siPackage.getCommands().get(0)) {
			result = result + s + " ";
			localVariables.add(s);
		}
		result += "] [ ";
		for (String s: siPackage.getCommands().get(1)) {
			String[] commandComponents = s.split(" ");
			for (String ss: commandComponents) {
				if (localVariables.contains(ss) || !ss.startsWith(":")) {
					result = result + ss + " ";
				} else {
					result = preParseVariable(ss) + " ";
				}
			}
		}
		result += "]";
		return result;
	}

	/**
     * Finds the corresponding value of a custom variable.
     * @param inputVariable
     * @return the value of the custom variable.
     */
    private String preParseVariable(String inputVariable) {
    	ArrayList<VariableNode> variableList
    			= myCommandTreeParser.getVariables();
    	String preParsedVariable = "0";
    	for (int j = 0; j < variableList.size(); ++j) {
    		String variableName = variableList.get(j).getName();
			if (inputVariable.equals(variableName)) {
				preParsedVariable = Integer
						.toString(variableList.get(j).getValue());
			}
		}
		return preParsedVariable;
	}

    /**
	 * Testing purpose.
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
	 */
	public static void main(String args[]) {
		CommandTreeParser parser = new CommandTreeParser("");
		VariableParser vp = new VariableParser(parser);

		int commandCount = 1;
		try {
			while(commandCount > 0) {
				String s = readUserInput("enter command: ");
				String result = vp.parse(s);
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
