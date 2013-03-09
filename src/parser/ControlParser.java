package parser;

import java.io.IOException;
import java.util.Queue;

/*
 * This class is the superclass for the control structure commands (if, ifelse, repeat, make, custom command
 * @author Junho Oh
 */
public abstract class ControlParser extends Parser {

	public ControlParser() {
	}
	
	public void checkSyntax(String command, Queue<String> myCommandParts) throws IOException{
		String ifCommand = command;
		while (!getSyntaxCheck().syntaxCheck(ifCommand)) { 
			ifCommand += " " + myCommandParts.remove();
		}
		makeNode(command);
	}
	
	public abstract void makeNode(String command);
}
