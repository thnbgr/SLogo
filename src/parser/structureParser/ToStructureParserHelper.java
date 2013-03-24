package parser.structureParser;

import java.io.IOException;
import java.util.ArrayList;

import parser.TreeMakingParser;
import parser.StructureInfoPackage;
import parser.node.Node;
import parser.node.control.CustomCommandNode;
/**
 * 
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class ToStructureParserHelper extends StructureParserHelper {
	private TreeMakingParser myParser;
	public ToStructureParserHelper(TreeMakingParser treeMakingParser) {
		myParser = treeMakingParser;
	}

	@Override
	public Node parser(String command) {
		try{
			StructureInfoPackage toStructPackage = myParser.getSyntaxCheck().splitToStructure(command);
			String commandName = toStructPackage.getValue();
			ArrayList<String> varNames = toStructPackage.getCommands().get(0);
			ArrayList<String> customCommands = toStructPackage.getCommands().get(1); 
									//changed to arraylist cuz there can be multiple commands
			CustomCommandNode temp = new CustomCommandNode(commandName, varNames, customCommands);
			myParser.addCustomCommands(temp);
			updateSyntax(varNames.size(), commandName);
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	private void updateSyntax(int prmSize, String commandName) throws IOException{
		StringBuilder customCommandRegex = new StringBuilder(); //start updating syntax
		customCommandRegex.append("(^");
		for (int i = 0; i<prmSize; ++i){
			customCommandRegex.append("\\s\\d+");
		}
		customCommandRegex.append(")");
		myParser.getSyntaxCheck().updateValidSyntax(commandName, customCommandRegex.toString());
	}

}
