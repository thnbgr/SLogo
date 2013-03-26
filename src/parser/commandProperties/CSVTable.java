package parser.commandProperties;

import java.io.*;
/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */

public class CSVTable extends CommandPropertiesTable{
	
	public CSVTable(String fileName) throws FileNotFoundException, IOException {
		super(fileName);
	}

	/**
	 * Reads the .csv file.
	 * @param fileName The file to read.
	 * @throws IOException 
	 */
	public void readFile(String fileName) throws IOException {

		BufferedReader CSVFile 
		= new BufferedReader(new FileReader(fileName));
		String dataRow = CSVFile.readLine();
		while (dataRow != null) {
			CommandProperties temp = new CommandProperties(dataRow);
			getRows().add(temp);
			dataRow = CSVFile.readLine();
		}
		CSVFile.close();

	}
}