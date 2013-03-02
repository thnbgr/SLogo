package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxCheck {
	
	public static final String COMMAND_REGEXS_FILE_NAME = "src/commandRegexs.csv";
	//static for testing purpose
	private static Map<String, String> validCommandRegex = new HashMap<String, String>();
	private static String lastCommandCall = "";
	private static int commandCallStartIndex = 0;
	private static int commandCallEndIndex = 0;
	
	public SyntaxCheck (){
		readFile(COMMAND_REGEXS_FILE_NAME);
	}
	
	private static void readFile(String fileName){
		try {
			BufferedReader CSVFile = new BufferedReader(new FileReader(fileName));
			String individualLine = CSVFile.readLine();
			while (individualLine != null){
				String[] individualCommandRegex = individualLine.split(",");
				validCommandRegex.put(individualCommandRegex[0],individualCommandRegex[1]);
				individualLine = CSVFile.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Does syntax checking before parsing. 
	 * Check doesn't include: invalid value and variable
	 * @param command the entire user input string
	 * @return boolean that represents whether the input string is valid
	 */
	public static boolean syntaxCheck(String command){ //static for testing propose
		if (command.equals("0")){
			System.out.println("valid command!!!!");
			return true;
		}
		//TODO: rename/organize code
		//TODO: missed commands e.g. ; commandS
							//e.g. FD :distance (possible situation??)
							//NOT DONE: TO (?); AND; OR; NOT (tests?)
		
		findLastCommand(command);
		
		if (lastCommandCall.equals("")){
			System.out.println("NO MATCHES!!"); //TODO: throw error here
		}else{
			String commandPattern2 = validCommandRegex.get(lastCommandCall);
			Pattern r2 = Pattern.compile(commandPattern2);
			Matcher m2 = r2.matcher(command).region(commandCallEndIndex, command.length());
			if (m2.find()){
				System.out.println(m2.group(1));
				int endIndex2 = m2.end();
				String simplifiedCommand = command.substring(0, commandCallStartIndex) + "0" + command.substring(endIndex2);
				System.out.println(simplifiedCommand);
				syntaxCheck(simplifiedCommand);
			}else{
				System.out.println("NO MATCHES!!!"); //TODO: throw error here
			}
		}
		return false;
	}

	
	private static String readUserInput(String printMessage) throws IOException{
        System.out.print(printMessage);
        InputStreamReader isr = new InputStreamReader ( System.in );
        BufferedReader br = new BufferedReader (isr);
        String returnString;
        try {
            returnString=br.readLine();
        } catch (IOException e) {
           throw new IOException(e);
        }
        return returnString;
    }
	
	private static void findLastCommand (String command){
		String commandPattern = "(\\w+)";
		
		Pattern r = Pattern.compile(commandPattern);
		
		Matcher m = r.matcher(command);
		while (m.find()){
			if (validCommandRegex.containsKey(m.group(1))){
				lastCommandCall = m.group(1);
				commandCallStartIndex = m.start();
				commandCallEndIndex = m.end();
				System.out.println("YAY! " + m.group(1));
			}
		}
	}
	
	public static void main(String args[]) {
		int commandCount = 10;
		SyntaxCheck sc = new SyntaxCheck();
		//readFile(COMMAND_REGEXS_FILE_NAME);
		try {
			while(commandCount > 0){
				String s = readUserInput("enter command: ");
				sc.syntaxCheck(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
