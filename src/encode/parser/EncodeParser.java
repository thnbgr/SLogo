package encode.parser;

import java.util.Map;

import command.CommandBundle;

public abstract class EncodeParser {
	
	public abstract void encode(CommandBundle myPackage);
	
	/*
	 * should probably not have a parser hierarchy....
	 * TODO: 
	 * 1. tokenize the string into operators and values
	 * 2. syntax check
	 * 3. build tree
	 * 
	 */
	
}
