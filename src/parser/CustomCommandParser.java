package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.node.VariableNode;
import parser.node.control.CustomCommandNode;

public class CustomCommandParser extends AbstractParser {

	private TreeMakingParser myTreeMakingParser;
	
	public CustomCommandParser(TreeMakingParser treeMakingParser) {
		myTreeMakingParser = treeMakingParser;
	}
	
	@Override
	public String parse(String command) throws IOException{
		String[] commandComponents = command.split(" ");
		
		//START: TESTING
		/**ArrayList<CustomCommandNode> temp = new ArrayList<CustomCommandNode>();
		CustomCommandNode ccn = new CustomCommandNode();
		ccn.setName("ssuumm");
		ccn.addVarName(":var1");
		ccn.addVarName(":var2");
		ccn.setCustomCommand("sum :var1 :var2 fd 5");
		temp.add(ccn);*/ //END: TESTING
    	
		ArrayList<String> customCommandList = new ArrayList<String>();
    	//for (CustomCommandNode c: temp){ //ADD FOR TESTING
		for (CustomCommandNode c: myTreeMakingParser.getCustomCommands()){ //DELETE FOR TESTING
        		customCommandList.add(c.getName());
        }
    	String preParsedCommand = "";
    	String current = "";
    	int i = 0;
    	while (i<commandComponents.length){
    		current = commandComponents[i];
    		if (customCommandList.contains(current)){
    			//CustomCommandNode matchedCommand = temp.get(customCommandList.indexOf(current)); //TESTING
    			CustomCommandNode matchedCommand = myTreeMakingParser.getCustomCommands().get(customCommandList.indexOf(current)); //DELETE FOR TESTING
    			List<String> inputParameters = new ArrayList<String>();
    			int prmStartIndex = i + 1;
    			for (int prmNumber = 0; prmNumber<matchedCommand.getVarNames().size(); ++prmNumber){
    				String validParameter = "";
    				if (isInteger(commandComponents[prmStartIndex])){
    					validParameter = commandComponents[prmStartIndex];
    				}else{
    					validParameter = myTreeMakingParser.getSyntaxCheck().findFirstValidCommand(commandComponents, prmStartIndex);
    				}
    				inputParameters.add(validParameter);
    				prmStartIndex += validParameter.split(" ").length;
    			}
    			String preParsedCustomCommand = preParseCustomCommand(matchedCommand, inputParameters);
    			preParsedCommand += preParsedCustomCommand;
    			i = prmStartIndex;
    		}else{
    			preParsedCommand += commandComponents[i] + " ";
    			i += 1;
    		}
    	}
    	return preParsedCommand.substring(0, preParsedCommand.length()-1);
	}
	
	private boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	private boolean isInteger(String s, int radix) {
	    if(s.isEmpty()){
	    	return false;
	    }
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1){
	            	return false;
	            }
	            else{
	            	continue;
	            }
	        }
	        if(Character.digit(s.charAt(i),radix) < 0){
	        	return false;
	        }
	    }
	    return true;
	}
	
    /**
     * Finds the corresponding command and replaces the parameters of a custom command name.
     * @param matchedCommand
     * @param inputParameters
     * @return
     */
	private String preParseCustomCommand(CustomCommandNode matchedCommand, List<String> inputParameters){
		Map<String, String> customedVariableList = makeCustomVariableList(matchedCommand, inputParameters);
		String customeCommand = matchedCommand.getCommand();
		String[] customeCommandSplited = customeCommand.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k<customeCommandSplited.length; k++){
			if (customedVariableList.containsKey(customeCommandSplited[k])){
				sb.append(customedVariableList.get(customeCommandSplited[k]) + " ");
			}else{
				sb.append(customeCommandSplited[k] + " ");
			}
		}
		String convertedCustomCommand = sb.toString(); 
		return convertedCustomCommand;
    }
	
	/**
	 * Creates a customed variable list for a specific custom command.
	 * @param matchedCommand
	 * @param inputParameters
	 * @return
	 */
    private Map<String, String> makeCustomVariableList(
			CustomCommandNode matchedCommand, List<String> inputParameters) {
    	//TODO: give local one priority
    	Map<String, String> customedVariableList = new HashMap<String, String>();
		for (VariableNode v: myTreeMakingParser.getVariables()){
			customedVariableList.put(v.getName(), Integer.toString(v.getValue()));
		}
		for (int j = 0; j<matchedCommand.getVarNames().size(); ++j){
			customedVariableList.put(matchedCommand.getVarNames().get(j), inputParameters.get(j));
		}
		return customedVariableList;
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
		CustomCommandParser ccp = new CustomCommandParser(parser);
		
		int commandCount = 10;
		try {
			while(commandCount > 0){
				String s = readUserInput("enter command: ");
				String result = ccp.parse(s);
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
