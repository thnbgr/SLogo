package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import parser.node.VariableNode;
import parser.node.control.CustomCommandNode;

public class VariableParser extends AbstractParser{
	
	private TreeMakingParser myParser;
	
	public VariableParser(TreeMakingParser treeMakingParser) {
		myParser = treeMakingParser;
	}
	
	@Override
	public String parse(String command){
		String[] commandComponents = command.split(" ");
    	String preParsedCommand = "";
    	String current = "";
    	int i = 0;
    	while (i<commandComponents.length){
    		String previous = current;
    		current = commandComponents[i];
    		if (current.startsWith(":") && !previous.equals("make")){
    			String preParsedVariable = preParseVairable(current);
    			preParsedCommand += preParsedVariable;
    			preParsedCommand += " ";
    		}else{
    			preParsedCommand += commandComponents[i] + " ";
    		}
    		i+=1;
    	}
    	return preParsedCommand.substring(0, preParsedCommand.length()-1);
	}
	
	/**
     * Finds the corresponding value of a custom variable.
     * @param inputVariable
     * @return the value of the custom variable.
     */
    private String preParseVairable(String inputVariable) {
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
