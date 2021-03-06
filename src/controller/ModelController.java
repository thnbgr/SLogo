package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observable;

import parser.CustomCommandParser;
import parser.EncodeTree;
import parser.AbstractParser;
import parser.CommandTreeParser;
import parser.SyntaxCheck;
import parser.SyntaxSpliter;
import parser.VariableParser;
import parser.node.control.CustomCommandNode;
import model.Model;

/**
*
* @author Junho Oh
* @author Wenshun Liu
*
* 		This class manages the entire process after an input string is
* 		entered. It returns an ArrayList of String of parsed commands to
* 		the view. The return type can also be easily changed into
* 		workspaces.
*/
public class ModelController extends Observable {

    private Model myModel;
    private SyntaxCheck mySyntaxCheck;
    private SyntaxSpliter mySyntaxSpliter;
    private static CommandTreeParser myTreeMakingParser
    				= new CommandTreeParser("");
    private AbstractParser[] myParsers=
    		{ new VariableParser(myTreeMakingParser),
    		new CustomCommandParser(myTreeMakingParser) };

    public ModelController (Model model) {
        myModel = model;
        mySyntaxCheck = new SyntaxCheck();
        mySyntaxSpliter = new SyntaxSpliter();
        myTreeMakingParser.setSyntaxSpliter(mySyntaxSpliter);
        mySyntaxSpliter.setSyntaxCheck(mySyntaxCheck);
        myTreeMakingParser.setSyntaxCheck(mySyntaxCheck);
        myModel.setController(this);
    }

    /**
     * Takes the user input string and checks its validity.
     * If valid calls processInputString to start processing.
     * @param inputCommand the user input string
     * @throws Exception 
     */
    public void checkInputValidAndProcess (String inputCommand)
    		throws Exception {
    	String[] individualInputCommands = inputCommand.split(" ; ");
        for (String s: individualInputCommands) {
        	if (mySyntaxCheck.syntaxCheck(s)) {
        		inputCommand.toLowerCase();
        		String preParsedCommand = inputCommand;
        		for (AbstractParser p: myParsers) {
        			preParsedCommand = p.parse(preParsedCommand);
        		}
        		processInputString(preParsedCommand);
        	} else {
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
	 * @throws Exception 
     */
    public void processInputString(String inputCommand) throws
    			Exception {
    	String[] splitedCommands = inputCommand.split(" ; ");
		for (String s: splitedCommands) {
			EncodeTree et = myTreeMakingParser.encode(s);
			String commandResult = myModel.decode(et.getHead());
			System.out.println(commandResult); //for testing purpose.

		}
		return;
    }

    /**
     * Calls CommandTreeParser to encode the given String.
     * @param command
     * @return
     * @throws Exception 
     */
    public EncodeTree encode(String command) throws 
    			Exception {
    	EncodeTree et = myTreeMakingParser.encode(command);
    	return et;
    }

    /**
     * Testing purpose for TO commands.
     */
    private static void getCustomCommand() {
    	ArrayList<CustomCommandNode> temp
    			= myTreeMakingParser.getCustomCommands();
    	for (CustomCommandNode ccn: temp) {
    		System.out.println(ccn.getName());
    		System.out.println(ccn.getVarNames());
    		System.out.println(ccn.getCommand());
    	}
    }

    /**
	 * Testing purpose.
	 */
	private static String readUserInput(String printMessage)
											throws IOException {
        System.out.print(printMessage);
        InputStreamReader isr = new InputStreamReader( System.in );
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
		int commandCount = 1;
		Model model = new Model();
		ModelController controller = new ModelController(model);

		try {
			while(commandCount > 0) {
				String s = readUserInput("enter command: ");
				controller.checkInputValidAndProcess(s);
				//getCustomCommand();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
