package parser.structureParser;

import java.io.IOException;
import java.util.ArrayList;

import parser.CommandTreeParser;
import parser.StructureInfoPackage;
import parser.node.Node;
import parser.node.control.CustomCommandNode;
/**
*
* @author Junho Oh
* @author Wenshun Liu
*
*         This class is responsible for parsing TO structures
*         and update its syntax.
*/
public class ToStructureParserHelper extends StructureParserHelper {
	public ToStructureParserHelper(CommandTreeParser treeMakingParser) {
		super(treeMakingParser);
	}

	/**
	 * Finds the components of a TO command. Records and updates its
	 * syntax.
	 * @throws IOException 
	 */
	@Override
	public Node parse(String command) throws IOException {
		StructureInfoPackage toStructPackage =
				getParser().getSyntaxSpliter()
				.splitControlStructure("to", true, command);

		String commandName = toStructPackage.getValue();
		ArrayList<String> varNames = toStructPackage.getCommands().get(0);
		ArrayList<String> customCommands =
				toStructPackage.getCommands().get(1); 
		CustomCommandNode temp =
				new CustomCommandNode(commandName,
						varNames, customCommands);
		getParser().addCustomCommands(temp);
		updateSyntax(varNames.size(), commandName);
		return new Node();
	}

	/**
	 * Creates and updates the syntax of a custom command, based on the
	 * number of parameters it will take.
	 * @param prmSize The parameter size of the custom command
	 * @param commandName The name of the custom command
	 * @throws IOException
	 */
	private void updateSyntax(int prmSize, String commandName) throws IOException{
		StringBuilder customCommandRegex = new StringBuilder();
		if (prmSize == 1) {
			customCommandRegex.append("(^$)");
		} else {
			customCommandRegex.append("(^");
			for (int i = 0; i < prmSize; ++i) {
				customCommandRegex.append("\\s\\d+");
			}
			customCommandRegex.append(")");
		}
		getParser().getSyntaxCheck()
			.updateValidSyntax(commandName, customCommandRegex.toString());
	}

	@Deprecated
	public Node parseChildren(Node valueNode,
			StructureInfoPackage controlStructPackage) {
		return null;
	}

	@Deprecated
	public String getType() {
		return null;
	}

	@Deprecated
	public boolean getHasValue() {
		return false;
	}

}
