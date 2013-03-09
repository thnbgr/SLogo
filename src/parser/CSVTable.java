package parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
class CSVRow{
	private String myCommand;
	private String mySymbol;
	private String myCommandNumArgs;
	private String myCommandFilePath;
	public CSVRow(String row){
		String[] dataArray = row.split(",");
		init(dataArray);
	}
	private void init(String[] dataArray){
		if(dataArray.length != 4){
			return;
		}
		myCommand = dataArray[0].trim();
		mySymbol = dataArray[1].trim();
		myCommandNumArgs = dataArray[2].trim();
		myCommandFilePath = dataArray[3].trim();
	}
	public boolean isValidCommand(String command){
		return myCommand.equals(command) || mySymbol.equals(command);
	}
	public int getCommandNumArgs(){
		return Integer.parseInt(myCommandNumArgs);
	}
	public String getCommandFilePath(){
		return myCommandFilePath;
	}
}
public class CSVTable{
	private ArrayList<CSVRow> myRows;

	public CSVTable(String fileName){
		myRows =  new ArrayList<CSVRow>();
		readFile(fileName);
	}
	public CSVRow returnCSVRow(String command){
		for(int i = 0; i < myRows.size();i++){
			if(myRows.get(i).isValidCommand(command)){
				return myRows.get(i);
			}
		}
		return null;
	}
	private void readFile(String fileName){
		try{
			BufferedReader CSVFile = new BufferedReader(new FileReader(fileName));
			String dataRow = CSVFile.readLine();
			while (dataRow != null){
				CSVRow temp = new CSVRow(dataRow);
				myRows.add(temp);
				dataRow = CSVFile.readLine();
			}
			CSVFile.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return;
		}
	} 
	
	
} 