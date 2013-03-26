package parser.structureParser;

import parser.CommandTreeParser;
import parser.StructureInfoPackage;
import parser.node.Node;
import parser.node.control.IfNode;

/**
*
* @author Junho Oh
* @author Wenshun Liu
*
*         This class is responsible for parsing IF structures
*         into the appropriate Node for evaluation, given the
*         StructureInfoPackage which separates its components.
*/
public class IfStructureParserHelper extends StructureParserHelper {
	public IfStructureParserHelper(CommandTreeParser treeMakingParser) {
		super(treeMakingParser);
	}

	/**
	 * Creates and returns the IfNode for a IF command,
	 * given its separated components.
	 */
	@Override
	public Node parseChildren(Node valueNode,
			StructureInfoPackage controlStructPackage) throws Exception {

		IfNode ifNode = new IfNode();
		Node ifCommands = new Node();

		ifNode.addChild(valueNode);
		addChildCommands(ifCommands, controlStructPackage
							.getCommands().get(0));
		ifNode.addChild(ifCommands);
		return ifNode;
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
	 * Gets the type of the command. In this case "if".
	 */
	@Override
	public String getType() {
		return "if";
	}
}
