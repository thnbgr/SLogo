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
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CustomCommandParser;
import parser.EncodeTree;
import parser.AbstractParser;
import parser.CommandTreeParser;
import parser.SyntaxCheck;
import parser.VariableParser;
import parser.node.VariableNode;
import parser.node.control.CustomCommandNode;
import view.Workspace;
import model.Model;


public class ModelController extends Observable {

    //private List<IView> myViewList;
    private Model myModel;
    private SyntaxCheck mySyntaxCheck;
    private static CommandTreeParser myTreeMakingParser = new CommandTreeParser("");
    private AbstractParser[] myParsers= {new VariableParser(myTreeMakingParser), new CustomCommandParser(myTreeMakingParser)};
    
    public ModelController (Model model) {
        myModel = model;
        mySyntaxCheck = new SyntaxCheck();
        myTreeMakingParser.setSyntaxCheck(mySyntaxCheck);
        //myTreeMakingParser = new TreeMakingParser(""); //add filename later 
        myModel.setController(this);
        myModel.setParser(myTreeMakingParser);
    }

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
        		String preParsedCommand = inputCommand;
        		for (AbstractParser p: myParsers){
        			preParsedCommand = p.parse(preParsedCommand);
        		}
        		//System.out.println(preParsedCommand);
        		processInputString(preParsedCommand);
        	}else{
        		throw new IOException();
        	}
        }
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
			EncodeTree et = myTreeMakingParser.encode(s);
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
   /** public void findLastStructure (String inputCommand){ //duplicate code w SyntaxCheck??
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
    }*/
    
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
    	EncodeTree et = myTreeMakingParser.encode(command);
    	return et;
    }
    
    /**
     * Testing purpose for TO commands.
     */
    private static void getCustomCommand(){
    	ArrayList<CustomCommandNode> temp = myTreeMakingParser.getCustomCommands();
    	for (CustomCommandNode ccn: temp){
    		System.out.println(ccn.getName());
    		System.out.println(ccn.getVarNames());
    		System.out.println(ccn.getCommand());
    	}
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
		ModelController controller = new ModelController(model);
		
		try {
			while(commandCount > 0){
				String s = readUserInput("enter command: ");
				controller.checkInputValidAndProcess(s);
				//getCustomCommand();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
