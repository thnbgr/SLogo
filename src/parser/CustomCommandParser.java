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

	private CommandTreeParser myTreeMakingParser;
	private List<String> myCustomCommandList;
	
	public CustomCommandParser(CommandTreeParser treeMakingParser) {
		myTreeMakingParser = treeMakingParser;
		myCustomCommandList = new ArrayList<String>();
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
		temp.add(ccn); 
		
		CustomCommandNode ccn2 = new CustomCommandNode();
		ccn2.setName("house");
		ccn2.addVarName(":var1");
		ccn2.addVarName(":var2");
		ccn2.setCustomCommand("fd :var1 ssuumm :var2 4");
		temp.add(ccn2);*///END: TESTING
    	
    	//for (CustomCommandNode c: temp){ //ADD FOR TESTING
		myCustomCommandList = new ArrayList<String>();
		for (CustomCommandNode c: myTreeMakingParser.getCustomCommands()){ //DELETE FOR TESTING
        		myCustomCommandList.add(c.getName());
        }
		
		if (!hasCustomCommand(commandComponents)){
			return command;
		}
		
    	String preParsedCommand = "";
    	String current = "";
    	int i = 0;
    	while (i<commandComponents.length){
    		current = commandComponents[i];
    		if (myCustomCommandList.contains(current)){
    			//CustomCommandNode matchedCommand = temp.get(myCustomCommandList.indexOf(current)); //TESTING
    			CustomCommandNode matchedCommand = myTreeMakingParser.getCustomCommands().get(myCustomCommandList.indexOf(current)); //DELETE FOR TESTING
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
    	return parse(preParsedCommand.substring(0, preParsedCommand.length()-1));
	}
	
	private boolean hasCustomCommand(String[] commandComponents){
		boolean result = false;
		for (String s: commandComponents){
			if (myCustomCommandList.contains(s)){
				result = true;
			}
		}
		return result;
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
		Map<String, String> localVariableList = makeLocalVariableList(matchedCommand, inputParameters);
		Map<String, String> globalVariableList = makeGlobalVariableList();
		ArrayList<String> customCommand = matchedCommand.getCommand();
		String entireCustomCommand = "";
		for (String s: customCommand){
			entireCustomCommand += s + " ";
		}
		String[] customeCommandSplited = entireCustomCommand.substring(0,entireCustomCommand.length()-1).split(" ");
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k<customeCommandSplited.length; k++){
			if (localVariableList.containsKey(customeCommandSplited[k])){
				sb.append(localVariableList.get(customeCommandSplited[k]) + " ");
			}else if (globalVariableList.containsKey(customeCommandSplited[k])){
				sb.append(globalVariableList.get(customeCommandSplited[k]) + " ");
			}
			else{
				sb.append(customeCommandSplited[k] + " ");
			}
		}
		String convertedCustomCommand = sb.toString(); 
		return convertedCustomCommand;
    }
	
	
    private Map<String, String> makeLocalVariableList(
			CustomCommandNode matchedCommand, List<String> inputParameters) {
    	Map<String, String> localVariableList = new HashMap<String, String>();
		for (int j = 0; j<matchedCommand.getVarNames().size(); ++j){
			localVariableList.put(matchedCommand.getVarNames().get(j), inputParameters.get(j));
		}
		return localVariableList;
	}
    
    private Map<String, String> makeGlobalVariableList() {
    	Map<String, String> globalVariableList = new HashMap<String, String>();
		for (VariableNode v: myTreeMakingParser.getVariables()){
			globalVariableList.put(v.getName(), Integer.toString(v.getValue()));
		}
		return globalVariableList;
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
		CommandTreeParser parser = new CommandTreeParser("");
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
