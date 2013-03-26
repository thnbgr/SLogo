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

	public IfElseStructureParserHelper(CommandTreeParser treeMakingParser) {
		super(treeMakingParser);
	}
	@Override
	public String getType() {
		return "ifelse";
	}
	
	@Override
	public Node parseChildren(Node valueNode, StructureInfoPackage controlStructPackage) {
		IfElseNode ifElseNode = new IfElseNode();
		Node ifCommands = new Node();
		Node ifElseCommands = new Node();
		ifElseNode.addChild(valueNode);
		try{
			addChildCommands(ifCommands, controlStructPackage.getCommands().get(0));
			addChildCommands(ifElseCommands, controlStructPackage.getCommands().get(1));
			ifElseNode.addChild(ifCommands);
			ifElseNode.addChild(ifElseCommands);
			return ifElseNode;
		}
		catch(Exception e){
			
		}
		return new Node();
	}

}
