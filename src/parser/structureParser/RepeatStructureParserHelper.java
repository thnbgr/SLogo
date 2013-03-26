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
 */
public class RepeatStructureParserHelper extends StructureParserHelper {

	public RepeatStructureParserHelper(CommandTreeParser treeMakingParser) {
		super(treeMakingParser);
	}
	@Override
	public String getType() {
		return "repeat";
	}

	@Override
	public Node parseChildren(Node valueNode, StructureInfoPackage controlStructPackage) {
		RepeatNode repeatNode = new RepeatNode();
		Node repeatCommands = new Node();
		repeatNode.addChild(valueNode);
		try{
			addChildCommands(repeatCommands, controlStructPackage.getCommands().get(0));
			repeatNode.addChild(repeatCommands);
			return repeatNode;
		}
		catch(Exception e){
			
		}
		return new Node();
	}



}
