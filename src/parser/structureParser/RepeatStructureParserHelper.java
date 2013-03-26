package parser.structureParser;

import parser.CommandTreeParser;
import parser.StructureInfoPackage;
import parser.node.Node;
import parser.node.control.RepeatNode;
/**
*
* @author Junho Oh
* @author Wenshun Liu
*
*         This class is responsible for parsing REPEAT structures
*         into the appropriate Node for evaluation, given the
*         StructureInfoPackage which separates its components.
*/
public class RepeatStructureParserHelper extends StructureParserHelper {

	public RepeatStructureParserHelper(CommandTreeParser treeMakingParser) {
		super(treeMakingParser);
	}

	/**
	 * Creates and returns the RepeatNode for a REPEAT command,
	 * given its separated components.
	 */
	@Override
	public Node parseChildren(Node valueNode,
				StructureInfoPackage controlStructPackage) throws Exception {
		RepeatNode repeatNode = new RepeatNode();
		Node repeatCommands = new Node();
		repeatNode.addChild(valueNode);
		addChildCommands(repeatCommands,
				controlStructPackage.getCommands().get(0));
			repeatNode.addChild(repeatCommands);
			return repeatNode;
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
		return "repeat";
	}
}
