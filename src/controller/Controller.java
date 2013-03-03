package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.EncodeTree;
import parser.EncodeParser;
import parser.SyntaxCheck;
import parser.node.Node;
import command.CommandBundle;
import model.Model;
import view.IView;


public class Controller{

    private List<IView> myViewList;
    private Model myModel;
    private SyntaxCheck mySyntaxCheck;
    private EncodeParser myParser;
    private String lastStructureCall = "";
	private int structureCallStartIndex = -1;
	private int structureCallEndIndex = -1;
	private int leftBracketIndex = -1;
	private int rightBracketIndex = -1;
    
    public Controller (Model model) {
        myModel = model;
        mySyntaxCheck = new SyntaxCheck();
        myParser = new EncodeParser();
        myModel.setController(this);
    }

    public void addView (IView view) {
        myViewList.add(view);
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
    public void checkInputValidAndProcess (String inputCommand) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        //BUG: actually not seperated by ;
    	//SAME BUG: SyntaxCheck need recursion to test different situations. Shittest thing.
    	String[] individualInputCommands = inputCommand.split(" ; ");
        for (String s: individualInputCommands){
        	if (mySyntaxCheck.syntaxCheck(s)) {
            	processInputString(s);
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
     */
    public void processInputString(String inputCommand) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
    	findLastStructure(inputCommand);
    	
    	if (lastStructureCall == null){
    		String[] splitedCommands = inputCommand.split(" ; ");
    		for (String s: splitedCommands){
    			EncodeTree et = myParser.encode(s);
    			Node commandResult = myModel.decode(et);
    			//TODO: update View;
    		}
    		return;
    	}
    	EncodeTree et = myParser.encode(inputCommand.substring(structureCallStartIndex, structureCallEndIndex));
		List<Node> structureResults = new ArrayList<Node>();
    	structureResults.add(myModel.decode(et)); // need test to see whether all added
    	if (structureCallStartIndex == 0){
    		for (Node n: structureResults){
    			//TODO: update View
    		}
    		return;
    	}
    	Node lastResult = structureResults.get(structureResults.size()-1);
    	double structureCallResultValue = lastResult.myValue;
    	inputCommand = inputCommand.substring(0, structureCallStartIndex) + Double.toString(structureCallResultValue) + inputCommand.substring(rightBracketIndex);
    	processInputString(inputCommand);
    }
    
    /**
     * Finds the last Structure call available in the list and stores its info.
     * If not found the info keeps the default values.
     * @param inputCommand
     */
    public void findLastStructure (String inputCommand){ //duplicate code w SyntaxCheck??
    	String controlStructurePattern = "(REPEAT.+\\]|IF.+\\]|IFELSE.+\\].+\\]|TO.+\\].+\\])";
    	Pattern r = Pattern.compile(controlStructurePattern);
    	Matcher m = r.matcher(inputCommand);
    	
    	while (m.find()){
    		lastStructureCall = m.group(1);
    		structureCallStartIndex = m.start();
			structureCallEndIndex = m.end();
    	}
    }
    
    /**
     * Identifies multiple commands in a single input separated by space.
     * @return
     */
    public ArrayList<String> splitMultipleCommands(String command){
    	//TODO
    	ArrayList<String> commands = new ArrayList<String>();
    	return commands;
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
     */
    public EncodeTree encode(String command) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException{
    	EncodeTree et = myParser.encode(command);
    	return et;
    }
    
    /**public List<Node> processIndividualStructure(String structureCommand) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException{
    	//String[] structureComponents = structureCommand.split(" [ ");
    	//List<String[]> structureCommandsList = new ArrayList<String[]>();
    	for (int i = 1; i<structureComponents.length; ++i){
    		String commandString = structureComponents[i].substring(0, structureComponents[i].length()-2);
    		String[] individualCommand = commandString.split(";"); //BUG
    		structureCommandsList.add(individualCommand); //CHANGE NEED: make structureNodes store list of commands
    	}
    	
    	
    	
    	String commandPattern2 = "\\[.+\\]";
		Pattern r2 = Pattern.compile(commandPattern2);
		Matcher m2 = r2.matcher(inputCommand).region(structureCallEndIndex, inputCommand.length());
		List<Node> structureResults = new ArrayList<Node>();
		if (m2.find()){
			leftBracketIndex = m2.start();
			rightBracketIndex = m2.end();
			String structureCall = inputCommand.substring(structureCallStartIndex, rightBracketIndex);
			String commands = inputCommand.substring(structureCallEndIndex + leftBracketIndex, structureCallEndIndex + rightBracketIndex);
			String[] individualCommands = commands.split(";");
			for (String s: individualCommands){ //actually just need the last one
				EncodeTree et = myParser.encode(inputCommand.substring(0, leftBracketIndex+1) + " " + s + " ]");
				structureResults.add(myModel.decode(et));
			}
		}
		return structureResults;
    }*/
}
