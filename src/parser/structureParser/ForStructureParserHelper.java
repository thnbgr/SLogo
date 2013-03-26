package parser.structureParser;

import java.util.ArrayList;

import parser.CommandTreeParser;
import parser.StructureInfoPackage;
import parser.node.Node;
import parser.node.VariableNode;
import parser.node.control.ForNode;

/**
*
* @author Junho Oh
* @author Wenshun Liu
*
*         This class is responsible for parsing FOR structures
*         into the appropriate Node for evaluation, given the
*         StructureInfoPackage which separates its components.
*/
public class ForStructureParserHelper extends StructureParserHelper {

	private int numLoopParameters = 4;

	public ForStructureParserHelper(CommandTreeParser treeParser) {
		super(treeParser);
	}

	/**
	 * Creates and returns the ForNode for a FOR command,
	 * given its separated components.
	 */
	@Override
	public Node parseChildren(Node valueNode,
			StructureInfoPackage controlStructPackage) throws Exception {
		ForNode forNode = new ForNode();
		forNode.addChild(new VariableNode(0,
				controlStructPackage.getCommands().get(0).get(0)));

		for (int i = 1; i < numLoopParameters; ++i) {
			Node value = new Node();
			ArrayList<String> interationCommands = new ArrayList<String>();
			interationCommands.add(controlStructPackage.
					getCommands().get(0).get(i));
			addChildCommands(value, interationCommands);
			forNode.addChild(value);

		}
		return forNode;
	}

	/**
	 * Gets the type of the command. In this case "for".
	 */
	@Override
	public String getType() {
		return "for";
	}

	/**
	 * Gets whether the command needs to take a value after the command
	 * name.
	 */
	@Override
	public boolean getHasValue() {
		return false;
	}

}
