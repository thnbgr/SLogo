package parser;

import java.io.IOException;

/**
*
* @author Junho Oh
* @author Wenshun Liu
*
* 			This is the abstract super class for VariableParser and
* 			CustomCommandParser. It is called before the command is parsed
* 			into a tree.
*/
public abstract class AbstractParser {
	public abstract String parse(String command) throws IOException;
}
