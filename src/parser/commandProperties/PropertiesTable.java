package parser.commandProperties;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesTable extends CommandPropertiesTable {

	public PropertiesTable(String fileName) throws FileNotFoundException, IOException {
		super(fileName);
	}

	@Override
	public void readFile(String fileName) throws IOException{
		Properties propertiesFile = new Properties();
		propertiesFile.load(new FileInputStream(fileName));
		
		String commandName = propertiesFile.getProperty("name");
		String classPath = propertiesFile.getProperty("class");

	}

}
