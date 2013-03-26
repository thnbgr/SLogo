package parser;

import java.io.IOException;

/**
*
* @author Junho Oh
* @author Wenshun Liu
*
*/
public abstract class AbstractParser {
	public abstract String parse(String command) throws IOException;
}
