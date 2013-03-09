package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.EncodeTree;
import parser.Parser;
import parser.SyntaxCheck;
import parser.node.VariableNode;
import parser.node.control.CustomCommandNode;
import model.Model;

/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class Controller{

    //private List<IView> myViewList;
    private Model myModel;
    private SyntaxCheck mySyntaxCheck;
    private Parser myParser;
    private String lastStructureCall = "";
    
    public Controller (Model model) {
        myModel = model;
        mySyntaxCheck = new SyntaxCheck();
        myParser = new Parser(""); //add filename later 
        myModel.setController(this);
    }

    //public void addView (IView view) {
    //    myViewList.add(view);
    //}

    /**
     * Takes the user input string and checks its validity.
     * If valid calls processInputString to process.
     * @param inputCommand the user input string
     * @throws IllegalArgumentException
     * @throws SecurityException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void checkInputValidAndProcess (String inputCommand) throws IOException, IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
    	String[] individualInputCommands = inputCommand.split(" ; ");
        for (String s: individualInputCommands){
        	if (mySyntaxCheck.syntaxCheck(s)) {
        		inputCommand.toLowerCase();
        		String preParsedCommand = preParseCustomCommandAndVariable(s);
        		processInputString(preParsedCommand);
        	}else{
        		throw new IOException();
        	}
        }
    }
    
    /**
     * Finds custom command names and variables in the input string. Replaces 
     * with actual commands and variable values.
     * @param command
     * @return
     * @throws IOException
     */
    public String preParseCustomCommandAndVariable(String command) throws IOException{
    	String[] commandComponents = command.split(" ");
    	ArrayList<String> customCommandList = new ArrayList<String>();
    	for (CustomCommandNode c: myParser.getCustomCommands()){
    		customCommandList.add(c.getName());
    	}
    	String preParsedCommand = "";
    	String current = "";
    	int i = 0;
    	while (i<commandComponents.length){
    		String previous = current;
    		current = commandComponents[i];
    		if (customCommandList.contains(current)){
    			CustomCommandNode matchedCommand = myParser.getCustomCommands().get(customCommandList.indexOf(current));
    			List<String> inputParameters = new ArrayList<String>();
    			int prmStartIndex = i + 1;
    			for (int prmNumber = 0; prmNumber<matchedCommand.getVarNames().size(); ++prmNumber){
    				String validParameter = mySyntaxCheck.findFirstValidCommand(commandComponents, prmStartIndex);
    				inputParameters.add(validParameter);
    				prmStartIndex += validParameter.split(" ").length;
    			}
    			String preParsedCustomCommand = preParseCustomCommand(matchedCommand, inputParameters);
    			preParsedCommand += preParsedCustomCommand;
    			i = prmStartIndex;
    		}else if (current.startsWith(":") && !previous.equals("make")){
    			String preParsedVariable = preParseVairable(current);
    			preParsedCommand += preParsedVariable;
    			preParsedCommand += " ";
    		}else{
    			preParsedCommand += commandComponents[i] + " ";
    			i += 1;
    		}
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
    	Map<String, String> customedVariableList = new HashMap<String, String>();
		for (VariableNode v: myParser.getVariables()){
			customedVariableList.put(v.getName(), Integer.toString(v.getValue()));
		}
		for (int j = 0; j<matchedCommand.getVarNames().size(); ++j){
			customedVariableList.put(matchedCommand.getVarNames().get(j), inputParameters.get(j));
		}
		return customedVariableList;
	}

	/**
     * Processes a valid user input string by calling Parser to encode and Model
     * to decode. Starts by finding and replacing all the structure calls
     * with their return values. Then processes the updated string that
     * consists only of operation calls.
     * @param inputCommand
     * @throws IllegalArgumentException
     * @throws SecurityException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws IOException 
     */
    public void processInputString(String inputCommand) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
    	String[] splitedCommands = inputCommand.split(" ; ");
		for (String s: splitedCommands){
			EncodeTree et = myParser.encode(s);
			String commandResult = myModel.decode(et);
			System.out.println(commandResult);
			//UPDATE VIEW HERE!!!!!!!
		}
		return;
    }
    
    /**
     * Finds the last Structure call available in the list and stores its info.
     * If not found the info keeps the default values.
     * @param inputCommand
     */
    public void findLastStructure (String inputCommand){ //duplicate code w SyntaxCheck??
    	String controlStructurePattern = "(REPEAT|IF|IFELSE|TO)";
    	Pattern r = Pattern.compile(controlStructurePattern);
    	Matcher m = r.matcher(inputCommand);
    	int structureCallStartIndex = -1;
    	int structureCallEndIndex = -1;
    	
    	while (m.find()){
    		lastStructureCall = m.group(1);
    		structureCallStartIndex = m.start();
			structureCallEndIndex = m.end();
			//System.out.println(lastStructureCall);
    	}
    	if (lastStructureCall != null){
    		//System.out.println("YAY");
    		String singleCommandPattern = "";
    		if (lastStructureCall.equals("REPEAT") || lastStructureCall.equals("IF")){
    			singleCommandPattern = "(\\])";
    		}else{
    			singleCommandPattern = "(\\].+\\])";
    		}
    		Pattern r2 = Pattern.compile(singleCommandPattern);
	    	Matcher m2 = r2.matcher(inputCommand);
	    	if (m2.find()){
    			structureCallEndIndex = m2.end();
    		}
    	}
    	//System.out.println(inputCommand.substring(structureCallStartIndex, structureCallEndIndex));
    }
    
    /**
     * Calls Parser to encode the given String.
     * @param command
     * @return
     * @throws IllegalArgumentException
     * @throws SecurityException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws IOException 
     */
    public EncodeTree encode(String command) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException{
    	EncodeTree et = myParser.encode(command);
    	return et;
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
		Model model = new Model();
		Controller controller = new Controller(model);
		
		try {
			while(commandCount > 0){
				String s = readUserInput("enter command: ");
				controller.checkInputValidAndProcess(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
