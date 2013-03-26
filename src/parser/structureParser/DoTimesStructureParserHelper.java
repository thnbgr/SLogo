package parser.structureParser;

import java.util.ArrayList;

import parser.CommandTreeParser;
import parser.StructureInfoPackage;
import parser.node.Node;
import parser.node.ValueNode;
import parser.node.VariableNode;
import parser.node.control.DoTimesNode;

/**
*
* @author Junho Oh
* @author Wenshun Liu
*
*         This class is responsible for parsing DOTIMES structures
*         into the appropriate Node for evaluation, given the
*         StructureInfoPackage which separates its components.
*/
public class DoTimesStructureParserHelper extends StructureParserHelper {

	public DoTimesStructureParserHelper(CommandTreeParser treeParser) {
		super(treeParser);
	}

	/**
	 * Creates and returns the DoTimesNode for a DOTIMES command,
	 * given its separated components.
	 */
	@Override
	public Node parseChildren(Node valueNode,
			StructureInfoPackage controlStructPackage) throws Exception {
		DoTimesNode doTimesNode = new DoTimesNode();
		doTimesNode.addChild(new VariableNode(0,
				controlStructPackage.getCommands().get(0).get(0)));

		Node numInterations = new Node();
		ArrayList<String> interationCommands = new ArrayList<String>();
		interationCommands.add(
				controlStructPackage.getCommands().get(0).get(1));
		addChildCommands(numInterations, interationCommands);
		doTimesNode.addChild(numInterations);

		Node doTimesCommands = new Node();
		addChildCommands(doTimesCommands,
				controlStructPackage.getCommands().get(1));
		doTimesNode.addChild(doTimesCommands);

		return doTimesNode;
	}

	/**
	 * Gets the type of the command. In this case "dotimes".
	 */
	@Override
	public String getType() {
		return "dotimes";
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
