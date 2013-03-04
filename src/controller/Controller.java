package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import command.CommandPerformer;
import command.CommandPreParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import parser.EncodeTree;
import parser.Parser;
import parser.SyntaxCheck;
import parser.node.Node;
import parser.node.VariableNode;
import model.Model;
import view.DisplayView;
import view.IView;
import view.InputView;


public class Controller implements Observer {

    private static final Dimension myDisplayViewSize = new Dimension(500, 500);
    private static final Dimension myInputViewSize = new Dimension(500, 600);
    private DisplayView myDisplayView;
    private InputView myInputView;
    private List<IView> myViewList;
    private Model myModel;
    private CommandPreParser myCommandPreParser;
    private CommandPerformer myCommandPerformer;
    private SyntaxCheck mySyntaxCheck;
    private Parser myParser;
    private String lastStructureCall = "";
    private int structureCallStartIndex = -1;
    private int structureCallEndIndex = -1;
    private String processedString;

    public static final String TITLE = "Output Display";

    public Controller (Model model) {
        myModel = model;
        myDisplayView = new DisplayView(myDisplayViewSize);
        myCommandPreParser = new CommandPreParser(myDisplayView);
        myCommandPreParser.addObserver(this);
        myCommandPerformer = new CommandPerformer(myDisplayView);
        createOutputJFrame();
        myInputView =
                new InputView("Command Inputs", "English", myCommandPreParser, myInputViewSize);
        myModel = model;
        mySyntaxCheck = new SyntaxCheck();
        myParser = new Parser();
        myModel.setController(this);
    }

    private void createOutputJFrame () {
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add our user interface components
        frame.getContentPane().add(myDisplayView, BorderLayout.CENTER);
        // display them
        frame.pack();
        frame.setVisible(true);
        myDisplayView.addTurtle();
        myDisplayView.start();

    }

    public void addView (IView view) {
        myViewList.add(view);
    }

    public void update (Observable o, Object a) {
        String myCommand = (String) a;

        try {
            checkInputValidAndProcess(myCommand);
        }
        catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        // This is where we send it to the model parser
        // We assume for now that is has been parsed

        myInputView.receiveReturnMessage(myCommandPerformer.sendAction(processedString));
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
        //BUG: actually not separated by ;
    	//SAME BUG: SyntaxCheck need recursion to test different situations. Shittest thing.
    	String[] individualInputCommands = inputCommand.split(" ; ");
        for (String s: individualInputCommands){
        	if (mySyntaxCheck.syntaxCheck(s)) {
        		inputCommand.toLowerCase();
        		String preParsedCommand = preParsing(s);
        		processInputString(preParsedCommand);
        	}else{
        		throw new IOException();
        	}
        }
    }
    
    public String preParsing(String command){
    	String[] commandComponents = command.split(" ");
    	for (int i = 1; i<commandComponents.length; ++i){
    		String current = commandComponents[i];
    		String previous = commandComponents[i-1];
    		if (current.startsWith(":") && !previous.equals("make")){
    			ArrayList<VariableNode> variableList = myParser.getVariables();
    			for (int j = 0; j<variableList.size(); ++j){
    				String variableName = variableList.get(j).getName();
    				if (current.equals(variableName)){
    					String variableValue = Integer.toString(variableList.get(j).getValue());
    					command = command.substring(0, command.indexOf(current)) + variableValue + command.substring(command.indexOf(current) + current.length());
    				}
    				else if (j == variableList.size()-1){
    					String variableValue = "0";
    					command = command.substring(0, command.indexOf(current)) + variableValue + command.substring(command.indexOf(current) + current.length());
    				}
    			}
    		}
    	}
    	return command;
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
			processedString = commandResult;
		}
		return;
    	
    	
    	/**findLastStructure(inputCommand);
    	
    	if (lastStructureCall == null){
    		String[] splitedCommands = inputCommand.split(" ; ");
    		for (String s: splitedCommands){
    			EncodeTree et = myParser.encode(s);
    			String commandResult = myModel.decode(et);
    			//UPDATE VIEW HERE!!!!!!!
    		}
    		return;
    	}
    	EncodeTree et = myParser.encode(inputCommand.substring(structureCallStartIndex, structureCallEndIndex));
		List<Node> structureResults = new ArrayList<Node>();
    	structureResults.add(myModel.structureDecode(et)); // need test to see whether all added
    	if (structureCallStartIndex == 0){
    		for (Node s: structureResults){
    			String result = ((TurtleCommandNode) s).toString();
    			//UPDATE VIEW HERE!!!!!!!
    		}
    		return;
    	}
    	Node lastResult = structureResults.get(structureResults.size()-1);
    	int StructureCallResultValue = -1;
    	if (lastResult instanceof TurtleCommandNode){
    		structureCallResultValue = lastResult.getValue();
    	}
    	int structureCallResultValue = lastResult.getValue();
    	inputCommand = inputCommand.substring(0, structureCallStartIndex) + Double.toString(structureCallResultValue) + inputCommand.substring(structureCallEndIndex);
    	processInputString(inputCommand);*/
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
    	System.out.println(inputCommand.substring(structureCallStartIndex, structureCallEndIndex));
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
    
}
