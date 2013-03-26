package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 *
 * @author Junho Oh
 * @author Wenshun Liu
 * 
 * 			The CSVTable class assists the reading of .csv files and
 * 			stores the values into its inner structure CSVRow.
 */
class CSVRow {
	private String myCommand;
	private String mySymbol;
	private String myCommandNumArgs;
	private String myCommandFilePath;
	private int myNumElements = 4;

	public CSVRow(String row) {
		String[] dataArray = row.split(",");
		init(dataArray);
	}

	/**
	 * Analyzes and creates a CSVRow based on the given String Array.
	 * @param dataArray The String array of a CSVRow's components.
	 */
	private void init(String[] dataArray) {
		if (dataArray.length != myNumElements){
			return;
		}
		myCommand = dataArray[0].substring(1, dataArray[0].length()-1).trim();
		mySymbol = dataArray[1].substring(1, dataArray[1].length()-1).trim();
		myCommandNumArgs = dataArray[2].substring(1, dataArray[2].length()-1).trim();
		myCommandFilePath = dataArray[3].substring(1, dataArray[3].length()-1).trim();
	}

	/**
	 * Checks if the input String is a valid command.
	 * @param command The input String to be tested
	 * @return whether the String is a valid.
	 */
	public boolean isValidCommand(String command) {
		return myCommand.equals(command) || mySymbol.equals(command);
	}

	/**
	 * Gets the number of arguments of a command.
	 * @return the number of arguments.
	 */
	public int getCommandNumArgs() {
		return Integer.parseInt(myCommandNumArgs);
	}

	/**
	 * Gets the command's path to its class.
	 * @return The string of the command's path.
	 */
	public String getCommandFilePath() {
		return myCommandFilePath;
	}
}

public class CSVTable {
	private ArrayList<CSVRow> myRows;

	public CSVTable(String fileName) {
		myRows =  new ArrayList<CSVRow>();
		readFile(fileName);
	}

	/**
	 * Checks for the CSVRow of the given command and returns the result.
	 * @param command
	 * @return
	 */
	public CSVRow returnCSVRow(String command) {
		for (int i = 0; i < myRows.size(); i++) {
			if (myRows.get(i).isValidCommand(command)) {
				return myRows.get(i);
			}
		}
		return null;
	}

	/**
	 * Reads the .csv file.
	 * @param fileName The file to read.
	 */
	private void readFile(String fileName) {
		try {
			BufferedReader csvFile
				= new BufferedReader(new FileReader(fileName));
			String dataRow = csvFile.readLine();
			while (dataRow != null) {
				CSVRow temp = new CSVRow(dataRow);
				myRows.add(temp);
				dataRow = csvFile.readLine();
			}
			csvFile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}
