package parser.structureParser;

import parser.CommandTreeParser;
import parser.StructureInfoPackage;
import parser.node.Node;
import parser.node.control.IfElseNode;
/**
 *
 * @author Junho Oh
 * @author Wenshun Liu
 *
 */
public class IfElseStructureParserHelper extends StructureParserHelper {

/**
*
* @author Junho Oh
* @author Wenshun Liu
*
*         This class is responsible for parsing IFELSE structures
*         into the appropriate Node for evaluation, given the
*         StructureInfoPackage which separates its components.
*/
	public IfElseStructureParserHelper(CommandTreeParser treeMakingParser) {
		super(treeMakingParser);
	}

	/**
	 * Creates and returns the IfElseNode for a IFELSE command,
	 * given its separated components.
	 */
	@Override
	public Node parseChildren(Node valueNode,
			StructureInfoPackage controlStructPackage) throws Exception {
		IfElseNode ifElseNode = new IfElseNode();
		Node ifCommands = new Node();
		Node ifElseCommands = new Node();
		ifElseNode.addChild(valueNode);
		addChildCommands(ifCommands,
				controlStructPackage.getCommands().get(0));
		addChildCommands(ifElseCommands,
				controlStructPackage.getCommands().get(1));
		ifElseNode.addChild(ifCommands);
		ifElseNode.addChild(ifElseCommands);
		return ifElseNode;
	}

	/**
	 * Gets whether the command needs to take a value after the command
	 * name.
	 */
	@Override
	public boolean getHasValue() {
		return true;
	}

	/**
	 * Gets the type of the command. In this case "ifelse".
	 */
	@Override
	public String getType() {
		return "ifelse";
	}
}
