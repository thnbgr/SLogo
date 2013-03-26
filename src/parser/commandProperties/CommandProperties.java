package parser.commandProperties;


public class CommandProperties {
	private String myName;
	private String myAltName;
	private String myCommandNumArgs;
	private String myCommandFilePath;
	private int myNumElements = 4;
	
	public CommandProperties(String row) {
		String[] dataArray = row.split(",");
		init(dataArray);
	}	
	public CommandProperties(String[] data) {
		init(data);
	}
	
	/**
	 * Analyzes and creates a CSVRow based on the given String Array.
	 * @param dataArray The String array of a CSVRow's components.
	 */
	private void init(String[] dataArray) {
		if (dataArray.length != myNumElements){
			return;
		}
		myName = dataArray[0].trim();
		myAltName = dataArray[1].trim();
		myCommandNumArgs = dataArray[2].trim();
		myCommandFilePath = dataArray[3].trim();
	}
	
	/**
	 * Checks if the input String is a valid command.
	 * @param command The input String to be tested
	 * @return whether the String is a valid.
	 */
	public boolean isValidCommand(String command) {
		return myName.equals(command) || myAltName.equals(command);
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