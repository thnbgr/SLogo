package parser.structureParser;

import parser.CommandTreeParser;
import parser.StructureInfoPackage;
import parser.node.Node;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
*
* @author Junho Oh
* @author Wenshun Liu
*
*         The super class that is responsible for parsing structures commands
*         into the appropriate Nodes, by first splitting the command
*         string into structure command components and then creating
*         the node.
*/
public abstract class StructureParserHelper {
	private CommandTreeParser myParser;
	
	public StructureParserHelper(CommandTreeParser treeParser) {
		myParser = treeParser;
	}

	/**
	 * Splits the string command into its components and creates the
	 * Node that will be used for decode evaluation.
	 * @return
	 * @throws Exception
	 */
	public Node parse(String command) throws Exception {
		StructureInfoPackage controlStructPackage =
				myParser.getSyntaxSpliter()
				.splitControlStructure(getType(),
						getHasValue(), command);
		Node controlValueNode =
				myParser.encode(controlStructPackage.getValue()).getHead();
		return parseChildren(controlValueNode, controlStructPackage);
	}

	/**
	 * Takes in an ArrayList of String. Creates a Node for each string,
	 * and adds to the node.
	 * @param node The node that new commands will be added as its children.
	 * @param childCommands The list of commands that will be converted
	 * into corresponding Nodes and add to Node.
	 * @throws Exception
	 */
	public void addChildCommands(Node node, ArrayList<String> childCommands) 
						throws Exception {
		for (String command : childCommands) {
			node.addChild(myParser.encode(command).getHead());
		}
	}

	/**
	 * Creates and returns the corresponding Node for the structure command,
	 * given its separated components.
	 */
	public abstract Node parseChildren(Node valueNode,
			StructureInfoPackage controlStructPackage) throws Exception;
	
	/**
	 * Gets the type of the command.
	 */
	public abstract String getType();
	
	/**
	 * Gets whether the command needs to take a value after the command
	 * name.
	 */
	public abstract boolean getHasValue();
	
	/**
	 * Gets the CommandTreeParser.
	 * @return The CommandTreeParser.
	 */
	public CommandTreeParser getParser() {
		return myParser;
	}

}
