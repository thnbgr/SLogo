package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import parser.node.VariableNode;
import parser.node.control.CustomCommandNode;

public class VariableParser extends AbstractParser{
	
	private TreeMakingParser myParser;
	
	public VariableParser(TreeMakingParser treeMakingParser) {
		myParser = treeMakingParser;
	}
	
	@Override
	public String parse(String command) throws IOException{
		String[] commandComponents = command.split(" ");
    	String preParsedCommand = "";
    	String current = "";
    	int i = 0;
    	while (i<commandComponents.length){
    		String previous = current;
    		current = commandComponents[i];
    		if (current.equals("to")){ //UGLY!!!!!!!!!!!!
    			String makeCommand = current;
				while (!myParser.getSyntaxCheck().syntaxCheck(makeCommand)){
					i += 1;
					makeCommand += " " + commandComponents[i];
				}
				StructureInfoPackage toPackage = myParser.getSyntaxCheck().splitControlStructure("to", makeCommand);
				//System.out.println(toPackage.getType());
				preParsedCommand += structureParse(toPackage) + " ";
				i += 1;
    		}else if (current.equals("dotimes")){
    			//TODO: these three methods should be able to combine
    		}else if (current.equals("for")){
    			//TODO
    		}else if (current.startsWith(":") && !previous.equals("make")){
    			String preParsedVariable = preParseVariable(current);
    			preParsedCommand += preParsedVariable;
    			preParsedCommand += " ";
    			i+=1;
    		}else{
    			preParsedCommand += commandComponents[i] + " ";
    			i+=1;
    		}
    	}
    	//System.out.println(preParsedCommand.substring(0, preParsedCommand.length()-1));
    	return preParsedCommand.substring(0, preParsedCommand.length()-1);
	}
	
	private String structureParse(StructureInfoPackage siPackage){
		String result = "";
		result += siPackage.getType() + " ";
		result += siPackage.getValue() + " ";
		List<String> localVariables = new ArrayList<String>();
		result += "[ ";
		for (String s: siPackage.getCommands().get(0)){
			result = result + s + " ";
			localVariables.add(s);
		}
		result += "] [ ";
		for (String s: siPackage.getCommands().get(1)){
			String[] commandComponents = s.split(" ");
			for (String ss: commandComponents){
				if (localVariables.contains(ss) || !ss.startsWith(":")){
					result = result + ss + " ";
				}else{
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
    	ArrayList<VariableNode> variableList = myParser.getVariables();
    	String preParsedVariable = "0";
    	for (int j = 0; j<variableList.size(); ++j){
			String variableName = variableList.get(j).getName();
			if (inputVariable.equals(variableName)){
				preParsedVariable = Integer.toString(variableList.get(j).getValue());
			}
		}
		return preParsedVariable;
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
		TreeMakingParser parser = new TreeMakingParser("");
		VariableParser vp = new VariableParser(parser);
		
		int commandCount = 10;
		try {
			while(commandCount > 0){
				String s = readUserInput("enter command: ");
				String result = vp.parse(s);
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
