package parser.commandProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class CommandPropertiesTable {
	private ArrayList<CommandProperties> myRows;
	
	public CommandPropertiesTable(String fileName) throws FileNotFoundException, IOException {
		myRows =  new ArrayList<CommandProperties>();
		readFile(fileName);
	}
	
	public ArrayList<CommandProperties> getRows(){
		return myRows;
	}
	
	/**
	 * Checks for the CSVRow of the given command and returns the result.
	 * @param command
	 * @return
	 */
	public CommandProperties returnPropertyRow(String command) {
		for (int i = 0; i < getRows().size(); i++) {
			if (getRows().get(i).isValidCommand(command)) {
				return getRows().get(i);
			}
		}
		return null;
	}
	
	/**
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 * 
	 * @param fileName The file to read.
	 */
	public abstract void readFile(String fileName) throws IOException, FileNotFoundException;
}
