package parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVTable{
	private ArrayList<ArrayList<String>> toReturn; 
	public CSVTable(String fileName){
		toReturn =  new ArrayList<ArrayList<String>>();
		readFile(fileName);
		
	}
	public String getString(int rowIndex, int colIndex){
		return toReturn.get(rowIndex).get(colIndex);
	}
	public boolean isValidCommand(String command){
		return toReturn.contains(command); //TODO: change this. 
	}
	public String returnNodeClassName(String command)
	{
		int commandIndex;
		for(int i = 0; i < toReturn.size();i++){
			if(toReturn.get(i).contains(command)){
				commandIndex = toReturn.get(i).indexOf(command);
				if(commandIndex == 0 || commandIndex == 1){
					//System.out.println("class name " + toReturn.get(i).get(3));
					return toReturn.get(i).get(3);
				}
			}
		}
		return null;
	}
	private void readFile(String fileName){
		try{
			BufferedReader CSVFile = new BufferedReader(new FileReader(fileName));
			String dataRow = CSVFile.readLine();
			while (dataRow != null){
				String[] dataArray = dataRow.split(",");
				ArrayList<String> temp = new ArrayList<String>();
				for (String item:dataArray) { 
					temp.add(item);
				}
				toReturn.add(temp);
				
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