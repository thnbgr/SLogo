package parser;

import java.io.IOException;

public abstract class AbstractParser {
	public abstract String parse(String command) throws IOException;
}
